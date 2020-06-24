package com.marshalpackersandmovers.mymarshal;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.ViewHolder> {

    private boolean fromSearch;
    private List<QuotationModel> quotationModelList;
    private Boolean quotation;
    private int lastPosition =-1;

    public List<QuotationModel> getQuotationModelList() {
        return quotationModelList;
    }

    public void setQuotationModelList(List<QuotationModel> quotationModelList) {
        this.quotationModelList = quotationModelList;
    }

    public boolean isFromSearch() {
        return fromSearch;
    }

    public void setFromSearch(boolean fromSearch) {
        this.fromSearch = fromSearch;
    }

    public QuotationAdapter(List<QuotationModel> quotationModelList, Boolean quotation) {
        this.quotationModelList = quotationModelList;
        this.quotation = quotation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotation_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String productID = quotationModelList.get(position).getProductID();
        String  resource = quotationModelList.get(position).getProductImage();
        String title = quotationModelList.get(position).getProductTitle();
        long freeCoupons = quotationModelList.get(position).getFreeCoupons();
        String rating = quotationModelList.get(position).getRating();
        long totalRatings = quotationModelList.get(position).getTotalRatings();
        String productPrice = quotationModelList.get(position).getProductPrice();
        String cuttedPrice = quotationModelList.get(position).getCuttedPrice();
        Boolean paymentMethod = quotationModelList.get(position).isCOD();
        boolean inStock = quotationModelList.get(position).isInStock();

        holder.setData(productID,resource,title,freeCoupons,rating,totalRatings,productPrice,cuttedPrice,paymentMethod,position,inStock);

        if(lastPosition <position) {
            lastPosition = position;
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
        }

    }

    @Override
    public int getItemCount() {
        return quotationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupons;
        private ImageView couponIcon;
        private TextView rating;
        private TextView totalRatings;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deleteBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupons = itemView.findViewById(R.id.free_coupons);
            couponIcon = itemView.findViewById(R.id.coupon_icon);
            rating = itemView.findViewById(R.id.tv_product_rating_miniview);
            totalRatings = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.total_ratings_miniview);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteBtn = itemView.findViewById(R.id.delete_button);
        }

        private void setData(final String productID, String resource, String title, long freeCouponsNo, String averageRate, long totalRatingsNo, String price, String cuttedPriceValue, Boolean payMethod, final int index, boolean inStock){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.icon_placeholder)).into(productImage);
            productTitle.setText(title);
            if(freeCouponsNo != 0 && inStock){
                couponIcon.setVisibility(View.VISIBLE);
                if (freeCouponsNo==1){
                    freeCoupons.setText("free "+freeCouponsNo+" coupon");
                }else {
                    freeCoupons.setText("free "+freeCouponsNo+" coupons");
                }
            }else {
                couponIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }
            LinearLayout linearLayout = (LinearLayout) rating.getParent();
            if (inStock){
                rating.setVisibility(View.VISIBLE);
                totalRatings.setVisibility(View.VISIBLE);
                productPrice.setText("OUT OF SERVICE");
                productPrice.setTextColor(Color.parseColor("#000000"));
                cuttedPrice.setVisibility(View.VISIBLE);
                rating.setText(averageRate);
                totalRatings.setText("( "+totalRatingsNo+" ratings)");
                productPrice.setText(price);
                cuttedPrice.setText(cuttedPriceValue);
                linearLayout.setVisibility(View.VISIBLE);
                if (payMethod){
                    paymentMethod.setVisibility(View.VISIBLE);
                }else{
                    paymentMethod.setVisibility(View.INVISIBLE);
                }

            }else {
                linearLayout.setVisibility(View.INVISIBLE);
                rating.setVisibility(View.INVISIBLE);
                totalRatings.setVisibility(View.INVISIBLE);
                productPrice.setText("OUT OF SERVICE");
                productPrice.setTextColor(itemView.getContext().getResources().getColor(R.color.colorPrimary));
                cuttedPrice.setVisibility(View.INVISIBLE);
                paymentMethod.setVisibility(View.INVISIBLE);
            }

            if (quotation){
                deleteBtn.setVisibility(View.VISIBLE);
            }else {
                deleteBtn.setVisibility(View.GONE);
            }

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!ProductDetailsActivity.running_wishlist_query ) {
                        ProductDetailsActivity.running_wishlist_query = true;
                        DBqueries.removeFromQuotation(index, itemView.getContext());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fromSearch){
                        ProductDetailsActivity.fromSearch = true;
                    }
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("PRODUCT_ID",productID);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
