/*  這個類別實作TimePickerDialog，用來讓使用者設定時間。
 * @author MingYao, 2022/05/24
 * @exception RuntimeException()
 *
 * 呼叫方式：
 * 1.可以輸入預設時間
 * DialogFragment TimeFragment = TimePickerFragment.newInstance(Calendar c);
 * TimeFragment.show(getSupportFragmentManager(), "timePicker");
 * 2.以目前時間為預設時間
 * DialogFragment TimeFragment = TimePickerFragment.newInstance();
 * TimeFragment.show(getSupportFragmentManager(), "timePicker");
 *
 * 回傳方式：
 * 實作 OnTimePickerFragmentListener 介面，實作 OnTimeSet(Calendar c)
 */

package abc123.gmail.com;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private OnTimePickerFragmentListener mListener = null;
    private static final String ARG_PARAM1 = "param1";
    Calendar c;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        c = Calendar.getInstance();
        Bundle args = getArguments();
        if (args != null) {
            c.setTimeInMillis(getArguments().getLong(ARG_PARAM1));
        }

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()))
//        {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                super.onTimeChanged(view, hourOfDay, minute);
//                onTimeSet(view, hourOfDay, minute);
//                this.dismiss();
//            }
//        }
        ;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        if (mListener != null) {
            mListener.onTimeSet(c);
        }
    }

    public static TimePickerFragment newInstance(Calendar c) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, c.getTimeInMillis());

        fragment.setArguments(args);
        return fragment;
    }

    public static TimePickerFragment newInstance() {
        TimePickerFragment fragment = new TimePickerFragment();
        return fragment;
    }

    // ================================================

    public interface OnTimePickerFragmentListener {
        void onTimeSet(Calendar c);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTimePickerFragmentListener) {
            mListener = (OnTimePickerFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTimePickerFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
