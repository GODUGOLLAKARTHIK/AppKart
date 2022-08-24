package com.example.appkart.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.appkart.Models.CategoryModel;
import com.example.appkart.Models.NewProductModel;
import com.example.appkart.R;
import com.example.appkart.adapter.CategoryAdapter;
import com.example.appkart.adapter.NewProductAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    //category recyclerview
    RecyclerView catRecyclerview;

    //newproduct recyclerview
    RecyclerView newProductRecyclerView;

    NewProductAdapter newProductAdapter;
    List<NewProductModel> newProductList;


    //category recycler view
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    FirebaseFirestore db;


        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_home, container, false);

            catRecyclerview = root.findViewById(R.id.rec_category);
            //newproduct
            newProductRecyclerView=root.findViewById(R.id.new_product_rec);

            db = FirebaseFirestore.getInstance();

            //image slider
            ImageSlider imageSlider = root.findViewById(R.id.image_slider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.banner1, "Discount on Shoe Items", ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.banner2, "Discount on Perfume", ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.banner3, "70% OFF", ScaleTypes.CENTER_CROP));

            imageSlider.setImageList(slideModels);

            //category

            catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), catRecyclerview.HORIZONTAL, false));
            categoryModelList = new ArrayList<>();
            categoryAdapter = new CategoryAdapter(getActivity(), categoryModelList);
            catRecyclerview.setAdapter((categoryAdapter));

            //reading data from cloudstore

            db.collection("Category")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                    categoryModelList.add(categoryModel);
                                    categoryAdapter.notifyDataSetChanged();

                                }
                            } else {

                            }
                        }
                    });

            //newproduct
            newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), newProductRecyclerView.HORIZONTAL, false));
            newProductList = new ArrayList<>();
            newProductAdapter = new NewProductAdapter(getContext(), newProductList);
            newProductRecyclerView.setAdapter((newProductAdapter));

            db.collection("New Products")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    NewProductModel newProductModel = document.toObject(NewProductModel.class);
                                    newProductList.add(newProductModel);
                                    newProductAdapter.notifyDataSetChanged();

                                }
                            } else {
                                Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            return root;

        }
}