package com.marshalpackersandmovers.mymarshal;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyQuotationFragment extends Fragment {

    public MyQuotationFragment() {
        // Required empty public constructor
    }

    private RecyclerView quotationRecyclerView;
    private Dialog loadingDialog;
    public static QuotationAdapter quotationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_my_quotation, container, false);

        ////loading dialog
        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();
        ////loading dialog

        quotationRecyclerView = view.findViewById(R.id.my_quotation_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        quotationRecyclerView.setLayoutManager( linearLayoutManager);

        if(DBqueries.quotationModelList.size()==0){
            DBqueries.quotationList.clear();
            DBqueries.loadQuotation(getContext(),loadingDialog,true);
        }else {
            loadingDialog.dismiss();
        }

        quotationAdapter = new QuotationAdapter(DBqueries.quotationModelList,true);
        quotationRecyclerView.setAdapter(quotationAdapter);
        quotationAdapter.notifyDataSetChanged();

        return view;
    }
}
