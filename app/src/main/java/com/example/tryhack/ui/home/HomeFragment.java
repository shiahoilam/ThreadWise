package com.example.tryhack.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.tryhack.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up click listeners for images
        setupImageClickListeners(view);

        return view;
    }

    // Method to set up click listeners for image views
    private void setupImageClickListeners(View view) {
        ImageView imageView1 = view.findViewById(R.id.imageView); // Ensure the IDs match your layout
        ImageView imageView2 = view.findViewById(R.id.imageView2);
        ImageView imageView3 = view.findViewById(R.id.imageView3);
        ImageView imageView4 = view.findViewById(R.id.imageView4);
        ImageView imageView5 = view.findViewById(R.id.imageView5);
        ImageView imageView6 = view.findViewById(R.id.imageView6);
        ImageView imageView7 = view.findViewById(R.id.imageView7);
        ImageView imageView8 = view.findViewById(R.id.imageView8);

        // URLs for each image
        String url1 = "https://mashu.co.uk/collections/new-arrivals"; // URL for first image
        String url2 = "https://copenhagencartel.dk/en?shpxid=f7cf4476-8224-4906-9da3-496660d2d43c"; // URL for second image
        String url3 = "https://amourvert.com/"; // URL for third image
        String url4 = "https://www.tentree.com/"; // URL for fourth image
        String url5 = "https://slate.com/human-interest/2019/07/can-zara-be-sustainable.html"; // URL for fifth image
        String url6 = "https://www.bi.edu/research/business-review/articles/2021/11/does-hm-genuinely-contribute-to-a-sustainable-future/#:~:text=H%26M%20consumes%20massive%20resources%20to%20produce%20billions%20of,resulting%20in%20enormous%20emissions%20through%20transportation%20and%20shipping.";
        String url7 = "https://www.wildmag.co.uk/post/zero-emissions-zero-accountability-the-truth-about-urban-outfitters#:~:text=%E2%97%8F%20It%20follows%20an%20unsustainable%20fast%20fashion%20model,actions%20to%20protect%20biodiversity%20in%20its%20supply%20chain.";
        String url8 = "https://yoursustainableguide.com/is-yesstyle-fast-fashion/#:~:text=No%2C%20YesStyle%20is%20not%20sustainable.%20Its%20clothing%20line,suck%20up%20massive%20energy%2C%20finite%20resources%2C%20and%20freshwater.";

        // Set onClickListeners for each ImageView
        imageView1.setOnClickListener(v -> openWebPage(url1));
        imageView2.setOnClickListener(v -> openWebPage(url2));
        imageView3.setOnClickListener(v -> openWebPage(url3));
        imageView4.setOnClickListener(v -> openWebPage(url4));
        imageView5.setOnClickListener(v -> openWebPage(url5));
        imageView6.setOnClickListener(v -> openWebPage(url6));
        imageView7.setOnClickListener(v -> openWebPage(url7));
        imageView8.setOnClickListener(v -> openWebPage(url8));
    }

    // Method to open a web page
    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
