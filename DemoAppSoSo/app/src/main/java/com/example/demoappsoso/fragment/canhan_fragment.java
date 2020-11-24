package com.example.demoappsoso.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoappsoso.KetQuaDai.KetquadaiActivity;
import com.example.demoappsoso.LoGan.LoGanActivity;
import com.example.demoappsoso.LoKet.ActivityLoKet;
import com.example.demoappsoso.R;
import com.example.demoappsoso.SoMo.SomoActivity;
import com.example.demoappsoso.ThamKhaoActivity;
import com.example.demoappsoso.ThongkeActivity;
import com.example.demoappsoso.TraCuu.TraCuuActivity;
import com.example.demoappsoso.ketqua_activity;
import com.example.demoappsoso.phantich.PhanTichActivity;

public class canhan_fragment extends Fragment {
    TextView txtKetQua;
    ImageView imgKetQua, imgThongKe, imgThamKhao, imgphantich, imgLoKet, imgSoMo,imgKetQuaDai,imgLoGan;
    LinearLayout tracuuLinear;

    public canhan_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        txtKetQua = view.findViewById(R.id.textViewKetQua);
//        txtKetQua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ketqua_activity.class);
//                startActivity(intent);
//            }
//        });
        imgKetQua = view.findViewById(R.id.imageKetQua);
        imgKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ketqua_activity.class);
                startActivity(intent);
            }
        });

        imgThongKe =view.findViewById(R.id.ImageViewThongke);
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThongkeActivity.class);
                startActivity(intent);
            }
        });
        imgThamKhao = view.findViewById(R.id.ImageViewThamKhao);
        imgThamKhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThamKhaoActivity.class);
                startActivity(intent);
            }
        });

        imgphantich = view.findViewById(R.id.imgThamKhao);
        imgphantich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhanTichActivity.class);
                startActivity(intent);
            }
        });
        imgLoKet = view.findViewById(R.id.LoKetCaNhan);
        imgLoKet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityLoKet.class);
                startActivity(intent);
            }
        });

        tracuuLinear = view.findViewById(R.id.TraCuu);
        tracuuLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TraCuuActivity.class);
                startActivity(intent);
            }
        });

        imgSoMo = view.findViewById(R.id.imageSomo);
        imgSoMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SomoActivity.class);
                startActivity(intent);
            }
        });

        imgKetQuaDai = view.findViewById(R.id.imageKetQuaDai);
        imgKetQuaDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KetquadaiActivity.class);
                startActivity(intent);
            }
        });
        imgLoGan = view.findViewById(R.id.imageLoGan);
        imgLoGan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoGanActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_canhan_fragment, container, false);
    }
}