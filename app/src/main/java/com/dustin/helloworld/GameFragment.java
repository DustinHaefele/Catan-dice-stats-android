package com.dustin.helloworld;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.services.CountService;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    private static final String COUNT_DTO = "countDto";

    private CountDto countDto  = CountService.initializeCountDto();

    private OnFragmentInteractionListener mListener;

    private DecimalFormat df = new DecimalFormat("###.##");

    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    TextView pct2;
    TextView pct3;
    TextView pct4;
    TextView pct5;
    TextView pct6;
    TextView pct7;
    TextView pct8;
    TextView pct9;
    TextView pct10;
    TextView pct11;
    TextView pct12;
    TextView cnt2;
    TextView cnt3;
    TextView cnt4;
    TextView cnt5;
    TextView cnt6;
    TextView cnt7;
    TextView cnt8;
    TextView cnt9;
    TextView cnt10;
    TextView cnt11;
    TextView cnt12;
    
    private View setTextViews(CountDto countDto, View view) {

        pct2 = (TextView) view.findViewById(R.id.pct2);
        cnt2 = (TextView) view.findViewById(R.id.cnt2);
        cnt2.setText(Integer.toString(countDto.getCountMap().get("two")));
        String percent2 = df.format(countDto.getCountStatsMap().get("two") * 100);
        pct2.setText(percent2);

        pct3 = (TextView) view.findViewById(R.id.pct3);
        cnt3 = (TextView) view.findViewById(R.id.cnt3);
        cnt3.setText(Integer.toString(countDto.getCountMap().get("three")));
        String percent3 = df.format(countDto.getCountStatsMap().get("three") * 100);
        pct3.setText(percent3);

        pct4 = (TextView) view.findViewById(R.id.pct4);
        cnt4 = (TextView) view.findViewById(R.id.cnt4);
        cnt4.setText(Integer.toString(countDto.getCountMap().get("four")));
        String percent4 = df.format(countDto.getCountStatsMap().get("four") * 100);
        pct4.setText(percent4);

        pct5 = (TextView) view.findViewById(R.id.pct5);
        cnt5 = (TextView) view.findViewById(R.id.cnt5);
        cnt5.setText(Integer.toString(countDto.getCountMap().get("five")));
        String percent5 = df.format(countDto.getCountStatsMap().get("five") * 100);
        pct5.setText(percent5);

        pct6 = (TextView) view.findViewById(R.id.pct6);
        cnt6 = (TextView) view.findViewById(R.id.cnt6);
        cnt6.setText(Integer.toString(countDto.getCountMap().get("six")));
        String percent6 = df.format(countDto.getCountStatsMap().get("six") * 100);
        pct6.setText(percent6);

        pct7 = (TextView) view.findViewById(R.id.pct7);
        cnt7 = (TextView) view.findViewById(R.id.cnt7);
        cnt7.setText(Integer.toString(countDto.getCountMap().get("seven")));
        String percent7 = df.format(countDto.getCountStatsMap().get("seven") * 100);
        pct7.setText(percent7);

        pct8 = (TextView) view.findViewById(R.id.pct8);
        cnt8 = (TextView) view.findViewById(R.id.cnt8);
        cnt8.setText(Integer.toString(countDto.getCountMap().get("eight")));
        String percent8 = df.format(countDto.getCountStatsMap().get("eight") * 100);
        pct8.setText(percent8);

        pct9 = (TextView) view.findViewById(R.id.pct9);
        cnt9 = (TextView) view.findViewById(R.id.cnt9);
        cnt9.setText(Integer.toString(countDto.getCountMap().get("nine")));
        String percent9 = df.format(countDto.getCountStatsMap().get("nine") * 100);
        pct9.setText(percent9);

        pct10 = (TextView) view.findViewById(R.id.pct10);
        cnt10 = (TextView) view.findViewById(R.id.cnt10);
        cnt10.setText(Integer.toString(countDto.getCountMap().get("ten")));
        String percent10 = df.format(countDto.getCountStatsMap().get("ten") * 100);
        pct10.setText(percent10);

        pct11 = (TextView) view.findViewById(R.id.pct11);
        cnt11 = (TextView) view.findViewById(R.id.cnt11);
        cnt11.setText(Integer.toString(countDto.getCountMap().get("eleven")));
        String percent11 = df.format(countDto.getCountStatsMap().get("eleven") * 100);
        pct11.setText(percent11);

        pct12 = (TextView) view.findViewById(R.id.pct12);
        cnt12 = (TextView) view.findViewById(R.id.cnt12);
        cnt12.setText(Integer.toString(countDto.getCountMap().get("twelve")));
        String percent12 = df.format(countDto.getCountStatsMap().get("twelve") * 100);
        pct12.setText(percent12);

        sendBack(countDto);
        return view;
    }

    public GameFragment() {
        // Required empty public constructor
    }

    public void setCountDto(CountDto countDto) {
        this.countDto = countDto;
    }


    public static GameFragment newInstance(CountDto count) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putSerializable(COUNT_DTO, count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countDto = (CountDto) getArguments().getSerializable(COUNT_DTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        if(null!=countDto) {
            setTextViews(countDto, view);
        }


        btn2 = view.findViewById(R.id.button2);
        btn3 = view.findViewById(R.id.button3);
        btn4 = view.findViewById(R.id.button4);
        btn5 = view.findViewById(R.id.button5);
        btn6 = view.findViewById(R.id.button6);
        btn7 = view.findViewById(R.id.button7);
        btn8 = view.findViewById(R.id.button8);
        btn9 = view.findViewById(R.id.button9);
        btn10 = view.findViewById(R.id.button10);
        btn11 = view.findViewById(R.id.button11);
        btn12 = view.findViewById(R.id.button12);

       
        btn2.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("two") + 1;
            countDto.getCountMap().put("two",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto, view);
        });
        btn2.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("two") - 1;
            countDto.getCountMap().put("two",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto, view);
            return true;
        });

        btn3.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("three") + 1;
            countDto.getCountMap().put("three",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto, view);
        });
        btn3.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("three") - 1;
            countDto.getCountMap().put("three",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto, view);
            return true;
        });

        btn4.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("four") + 1;
            countDto.getCountMap().put("four",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn4.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("four") - 1;
            countDto.getCountMap().put("four",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn5.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("five") + 1;
            countDto.getCountMap().put("five",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn5.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("five") - 1;
            countDto.getCountMap().put("five",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn6.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("six") + 1;
            countDto.getCountMap().put("six",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn6.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("six") - 1;
            countDto.getCountMap().put("six",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn7.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("seven") + 1;
            countDto.getCountMap().put("seven",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn7.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("seven") - 1;
            countDto.getCountMap().put("seven",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn8.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("eight") + 1;
            countDto.getCountMap().put("eight",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn8.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("eight") - 1;
            countDto.getCountMap().put("eight",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn9.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("nine") + 1;
            countDto.getCountMap().put("nine",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn9.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("nine") - 1;
            countDto.getCountMap().put("nine",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn10.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("ten") + 1;
            countDto.getCountMap().put("ten",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn10.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("ten") - 1;
            countDto.getCountMap().put("ten",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn11.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("eleven") + 1;
            countDto.getCountMap().put("eleven",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn11.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("eleven") - 1;
            countDto.getCountMap().put("eleven",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        btn12.setOnClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("twelve") + 1;
            countDto.getCountMap().put("twelve",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
        });
        btn12.setOnLongClickListener(v -> {
            Integer newCount = countDto.getCountMap().get("twelve") - 1;
            countDto.getCountMap().put("twelve",newCount);
            CountService.updateStats(countDto);
            setTextViews(countDto,view);
            return true;
        });

        return view;
    }

    public void sendBack(CountDto countDto) {
        if(mListener !=null) {
            mListener.onFragmentInteraction(countDto);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(CountDto countDto);
    }
}