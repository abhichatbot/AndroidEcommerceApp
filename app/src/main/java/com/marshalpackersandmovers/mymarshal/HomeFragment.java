package com.marshalpackersandmovers.mymarshal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

import static com.marshalpackersandmovers.mymarshal.DBqueries.categoryModelList;
import static com.marshalpackersandmovers.mymarshal.DBqueries.firebaseFirestore;
import static com.marshalpackersandmovers.mymarshal.DBqueries.lists;
import static com.marshalpackersandmovers.mymarshal.DBqueries.loadCategories;
import static com.marshalpackersandmovers.mymarshal.DBqueries.loadFragmentData;
import static com.marshalpackersandmovers.mymarshal.DBqueries.loadedCategoriesNames;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    public static SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView categoryRecyclerView;
    private List<CategoryModel> categoryModelfakeList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerview;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();
    private HomePageAdapter adapter;
    private ImageView noInternetConnection;
    private Button retryBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);
        homePageRecyclerview = view.findViewById(R.id.home_page_recyclerview);
        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorPrimary), getContext().getResources().getColor(R.color.colorPrimary), getContext().getResources().getColor(R.color.colorPrimary));
        retryBtn = view.findViewById(R.id.retry_btn);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        final LinearLayoutManager homePageRecyclerviewLayoutManager = new LinearLayoutManager(getContext());
        homePageRecyclerviewLayoutManager.setOrientation(RecyclerView.VERTICAL);
        homePageRecyclerview.setLayoutManager(homePageRecyclerviewLayoutManager);


        //////categories fake list
        categoryModelfakeList.add(new CategoryModel("null", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        categoryModelfakeList.add(new CategoryModel("", ""));
        //////categories fake list

        //////home fake list
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null", "#DFDFDF"));
        sliderModelFakeList.add(new SliderModel("null", "#DFDFDF"));
        sliderModelFakeList.add(new SliderModel("null", "#DFDFDF"));
        sliderModelFakeList.add(new SliderModel("null", "#DFDFDF"));
        sliderModelFakeList.add(new SliderModel("null", "#DFDFDF"));
        sliderModelFakeList.add(new SliderModel("null", "#DFDFDF"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList = new ArrayList<>();
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));

        homePageModelFakeList.add(new HomePageModel(0, sliderModelFakeList));
        homePageModelFakeList.add(new HomePageModel(1, "", "#DFDFDF"));
        homePageModelFakeList.add(new HomePageModel(2, "", "#DFDFDF", horizontalProductScrollModelFakeList, new ArrayList<QuotationModel>()));
        homePageModelFakeList.add(new HomePageModel(3, "", "#DFDFDF", horizontalProductScrollModelFakeList));

        //////home fake list
        categoryAdapter = new CategoryAdapter(categoryModelfakeList);

        adapter = new HomePageAdapter(homePageModelFakeList);

        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecyclerview.setVisibility(View.VISIBLE);

            if (categoryModelList.size() == 0) {
                loadCategories(categoryRecyclerView, getContext());
            } else {
                categoryAdapter = new CategoryAdapter(categoryModelList);
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerView.setAdapter(categoryAdapter);
            if (lists.size() == 0) {
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<HomePageModel>());
                loadFragmentData(homePageRecyclerview, getContext(), 0, "HOME");
            } else {
                adapter = new HomePageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }
            homePageRecyclerview.setAdapter(adapter);
        } else {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecyclerview.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.tenor).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
        }

        //////refresh layout

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPage();
            }
        });
        ////refresh layout
        return view;
    }

    private void reloadPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
//        categoryModelList.clear();
//        lists.clear();
//        loadedCategoriesNames.clear();
        DBqueries.clearData();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            retryBtn.setVisibility(View.GONE);
            noInternetConnection.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecyclerview.setVisibility(View.VISIBLE);
            categoryAdapter = new CategoryAdapter(categoryModelfakeList);
            adapter = new HomePageAdapter(homePageModelFakeList);
            categoryRecyclerView.setAdapter(categoryAdapter);
            homePageRecyclerview.setAdapter(adapter);

            loadCategories(categoryRecyclerView, getContext());
            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageModel>());
            loadFragmentData(homePageRecyclerview, getContext(), 0, "HOME");
        } else {

            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            Toast.makeText(getContext(),"No internet connection",Toast.LENGTH_SHORT).show();
            retryBtn.setVisibility(View.VISIBLE);
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecyclerview.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.tenor).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);

            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
