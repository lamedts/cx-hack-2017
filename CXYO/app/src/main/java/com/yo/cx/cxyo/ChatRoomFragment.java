package com.yo.cx.cxyo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;


public class ChatRoomFragment extends Fragment {

    ImageButton BackButton, SendButton, MediaButton;
    TextView TargetName;
    EditText MessageText;

    ListView MessengesList;
    ArrayList<String> messages = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat_room, container, false);

        setupUI(rootView);

        BackButton = (ImageButton)rootView.findViewById(R.id.back_button);
        SendButton = (ImageButton)rootView.findViewById(R.id.SendButton);
        MediaButton = (ImageButton)rootView.findViewById(R.id.MediaButton);
        TargetName = (TextView)rootView.findViewById(R.id.target_name);
        MessageText = (EditText)rootView.findViewById(R.id.MessageText);
        MessengesList = (ListView)rootView.findViewById(R.id.MessageList);

        TargetName.setText(getArguments().getString("target"));

        adapter = new ArrayAdapter<String>(getContext(), R.layout.list_message_item, R.id.message_item, messages);

        MessengesList.setAdapter(adapter);

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkFragment t = new NetworkFragment();

                Bundle bundle = new Bundle();
                bundle.putString("from", "chatroom");

                t.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, t).commit();
            }
        });

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(MessageText.getText().toString());
                MessageText.setText("");


                InputMethodManager inputManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        MessageText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (i)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            adapter.add(MessageText.getText().toString());
                            MessageText.setText("");

                        InputMethodManager inputManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);


                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        return rootView;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}
