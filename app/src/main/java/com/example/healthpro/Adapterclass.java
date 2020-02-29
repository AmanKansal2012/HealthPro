package com.example.healthpro;
import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class Adapterclass extends RecyclerView.Adapter<Adapterclass.MyViewHolder>{

    ArrayList<Users> list;
    public Adapterclass(ArrayList<Users> list) {
        this.list = list;


    }

    @NonNull
    @Override
    public Adapterclass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  final  Adapterclass.MyViewHolder holder, final int position) {
        MyViewHolder.name.setText(list.get(position).getName());
        MyViewHolder.desc.setText(list.get(position).getEmail());
        MyViewHolder.num.setText(list.get(position).getNumber());



    }

    @Override
    public int getItemCount() {
        return list.size();    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        static TextView name;
        static TextView desc;
        static TextView num;

        static RelativeLayout relate;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.description);
            relate=itemView.findViewById(R.id.doctor_view);
            num=itemView.findViewById(R.id.number);






        }
    }






}
