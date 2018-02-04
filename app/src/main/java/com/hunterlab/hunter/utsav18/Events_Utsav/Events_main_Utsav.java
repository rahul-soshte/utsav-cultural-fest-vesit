package com.hunterlab.hunter.utsav18.Events_Utsav;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hunterlab.hunter.utsav18.R;
import com.hunterlab.hunter.utsav18.RecyclerTouchListener;
import com.hunterlab.hunter.utsav18.SimpleDividerItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Events_main_Utsav.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Events_main_Utsav#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Events_main_Utsav extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference rootRef,childRef;
    private ArrayList<EventCat> catsArrayList=new ArrayList<>();
    private EditText searchField;
    // TODO: Rename and change types of parameters
    //private EventCatAdapter authorAdapter;
    private OnFragmentInteractionListener mListener;
    public Events_main_Utsav() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Events_main_Utsav.
     */
    // TODO: Rename and change types and number of parameters
    public static Events_main_Utsav newInstance(String param1, String param2) {
        Events_main_Utsav fragment = new Events_main_Utsav();
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
        return inflater.inflate(R.layout.fragment_events_main__utsav, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        final EventCatAdapter eventCatAdapter = new EventCatAdapter(catsArrayList);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        recyclerView.setAdapter(eventCatAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
              //  Author author= authorArrayList.get(position);
                //Toast.makeText(getActivity().getApplicationContext(),author.getId(),Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(getActivity().getApplicationContext(),QuoteActivity.class);
                //intent.putExtra("id",author.getId());
               // startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        rootRef.child("Utsav18").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
               catsArrayList.clear();
                for(DataSnapshot catsnapshot:dataSnapshot.getChildren())
                {
                 //     String Categories=authorsnapshot.getValue(String.class);
                    EventCat eventCat12=catsnapshot.getValue(EventCat.class);
                    String id=catsnapshot.getKey();
                    eventCat12.setId(id);
                    catsArrayList.add(eventCat12);
                }
                eventCatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
