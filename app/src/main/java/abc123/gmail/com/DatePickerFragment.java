/*  這個類別實作DatePickerDialog，用來讓使用者設定日期。
* @author MingYao, 2022/05/24
* @exception RuntimeException()
*
* 呼叫方式：
* 1.可以輸入預設日期
* DialogFragment DateFragment = DatePickerFragment.newInstance(Calendar c);
* DateFragment.show(getSupportFragmentManager(), "datePicker");
* 2.以目前時間為預設日期
* DialogFragment DateFragment = DatePickerFragment.newInstance();
* DateFragment.show(getSupportFragmentManager(), "datePicker");
*
* 回傳方式：
* 實作 OnDatePickerFragmentListener 介面，實作 OnDateSet(Calendar c)
 */

package abc123.gmail.com;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private OnDatePickerFragmentListener mListener = null;
    private static final String ARG_PARAM1 = "param1";
    Calendar c;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        c = Calendar.getInstance();
        Bundle args = getArguments();
        if (args != null) {
            c.setTimeInMillis(getArguments().getLong(ARG_PARAM1));
        }

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day) {
            @Override
            public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
                super.onDateChanged(view, year, month, dayOfMonth);
                onDateSet(view, year, month, dayOfMonth);
                this.dismiss();
            }
        };
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        if (mListener != null) {
            mListener.onDateSet(c);
        }
    }

    public static DatePickerFragment newInstance(Calendar c) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, c.getTimeInMillis());

        fragment.setArguments(args);
        return fragment;
    }

    public static DatePickerFragment newInstance() {
        DatePickerFragment fragment = new DatePickerFragment();
        return fragment;
    }

    // ================================================

    public interface OnDatePickerFragmentListener {
        void onDateSet(Calendar c);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDatePickerFragmentListener) {
            mListener = (OnDatePickerFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDatePickerFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}