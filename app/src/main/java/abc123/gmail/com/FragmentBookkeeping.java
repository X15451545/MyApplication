package abc123.gmail.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentBookkeeping extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentBookkeeping() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBookkeeping.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBookkeeping newInstance(String param1, String param2) {
        FragmentBookkeeping fragment = new FragmentBookkeeping();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.bookkeeping_constraintlayout, container, false);

        uiInit(rootView);
        return rootView;
    }

    TextView theDate, theTime;
    Button btSave;

    private void uiInit(View v) {
        theDate = v.findViewById(R.id.textView6);
        theTime = v.findViewById(R.id.textView7);
        btSave = v.findViewById(R.id.button2);
    }

    @Override
    public void onStart() {
        super.onStart();
        varInit();
        setUiListener();
    }

    private void varInit() {
    }


    private void setUiListener() { // ui監聽 寫在onStart()中
        theDate.setOnClickListener(this);
        theTime.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }


    @Override
    public void onStop() {
        releaseUiListener();
        super.onStop();
    }

    private void releaseUiListener() {
        theDate.setOnClickListener(null);
        theTime.setOnClickListener(null);
        btSave.setOnClickListener(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                // 帳務資料儲存

                // 返回主畫面
                startActivity(new Intent(getContext(), MainActivity.class));
                // Bookkeeping.this.finish();
                break;
        }
    }

}