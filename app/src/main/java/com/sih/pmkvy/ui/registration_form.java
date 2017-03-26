package com.sih.pmkvy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;
import static com.sih.pmkvy.R.id.city;
import static java.security.AccessController.getContext;

/**
 * Created by hp on 25-03-2017.
 */

public class registration_form extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText rname, remail, rcontact, rdob, rpincode, rfather, rincome, rstate, rdistrict, rstreet, rqualif, rworkexpinmonth, rworkindetail, rpreferedsec, rprefcourse, rmaxfees;
    Spinner rcategory;
    Button b2;
    RadioGroup gender,disable,relocate,agree,bpl;
    String name,mail,contact,pincode,fname,dob,income,street,district,state,qualification,workindetail,workinmonth,preferedsector,preferedcourse,maxfees;
    RadioButton gender_radio, disable_true, disable_false, bpl_true, bpl_false, relocate_true, relocate_false, agree_true, agree_false;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        rname = (EditText) findViewById(R.id.candidate_name);
        remail = (EditText) findViewById(R.id.candidate_email);
        rpincode = (EditText) findViewById(R.id.candidate_pincode);
        rcontact = (EditText) findViewById(R.id.candidate_contact_number);
        rdob = (EditText) findViewById(R.id.candidate_dob);
        rfather = (EditText) findViewById(R.id.candidate_f_name);
        rincome = (EditText) findViewById(R.id.student_annual_family_income);
        rstreet = (EditText) findViewById(R.id.candidate_address);
        rstate = (EditText) findViewById(R.id.candidate_state_ut);
        rdistrict = (EditText) findViewById(R.id.candidate_district);
        rqualif = (EditText) findViewById(R.id.candidate_educational_qualification);
        rworkexpinmonth = (EditText) findViewById(R.id.candidate_work_experience_months);
        rworkindetail = (EditText) findViewById(R.id.candidate_work_experience_desc);
        rpreferedsec = (EditText) findViewById(R.id.candidate_preferred_sector);
        rprefcourse = (EditText) findViewById(R.id.student_preferred_course);
        rmaxfees = (EditText) findViewById(R.id.candidate_max_fees);


        b2 = (Button) findViewById(R.id.submit);
        b2.setOnClickListener(this);

         name = rname.getText().toString();
         mail = remail.getText().toString();
         contact = rcontact.getText().toString();
         pincode = rpincode.getText().toString();
         dob = rdob.getText().toString();
         fname = rfather.getText().toString();
         income = rincome.getText().toString();
         street = rstreet.getText().toString();
         district = rdistrict.getText().toString();
         state = rstate.getText().toString();
         qualification = rqualif.getText().toString();
         workinmonth = rworkexpinmonth.getText().toString();
         workindetail = rworkindetail.getText().toString();
         preferedsector = rpreferedsec.getText().toString();
         preferedcourse = rprefcourse.getText().toString();
         maxfees = rmaxfees.getText().toString();

        gender=(RadioGroup)findViewById(R.id.reg_gender);
        disable=(RadioGroup)findViewById(R.id.disable_reg);
        relocate=(RadioGroup)findViewById(R.id.relocate_reg);
        agree=(RadioGroup)findViewById(R.id.agree_reg);
        bpl=(RadioGroup)findViewById(R.id.bpl_reg);




        rcategory = (Spinner) findViewById(R.id.student_category);
        rcategory.setOnItemSelectedListener(this);


        List<String> states = new ArrayList<String>();
        states.add("General");
        states.add("OBC");
        states.add("SC");
        states.add("ST");


        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, simple_spinner_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rcategory.setAdapter(stateAdapter);


    }

    @Override
    public void onClick(View v) {
        if(Check_data())
        {

            new register_info_data(this,v.getContext());

        }

        /*male=(RadioButton)findViewById(R.id.candidate_male);
        female=(RadioButton)findViewById(R.id.candidate_female);
        disable_true=(RadioButton)findViewById(R.id.candidate_disabled_true);
        disable_false=(RadioButton)findViewById(R.id.candidate_disabled_false);
        bpl_true=(RadioButton)findViewById(R.id.candidate_bpl_true);
        bpl_false=(RadioButton)findViewById(R.id.candidate_bpl_false);
        agree_true=(RadioButton)findViewById(R.id.candidate_agree_true);
        agree_false=(RadioButton)findViewById(R.id.candidate_agree_false);
        relocate_true=(RadioButton)findViewById(R.id.candidate_agree_true);
        relocate_false=(RadioButton)findViewById(R.id.candidate_agree_false);*/


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    boolean Check_data() {
        boolean flag = true;

        if (name == null || name.length() <= 3) {
            rname.setError("Name must be greater than 3 characters");
            flag = false;
        } else {
            rname.setError(null);
        }

        if (mail == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(remail.getText()).matches()) {
            flag = false;
            remail.setError("Invalid Email Addresss");

        } else {
            remail.setError(null);
        }

        if (dob == null || dob.length() <= 6) {
            flag = false;
            rdob.setError("Enter Correct dob");

        } else {
            rdob.setError(null);
        }

        if (fname == null || fname.length() <= 3) {
            flag = false;
            rfather.setError("Name must be greter than 3 letters");

        } else {
            rfather.setError(null);
        }
        if (pincode == null || pincode.length() <= 6) {
            flag = false;
            rpincode.setError("Enter Correct pincode");

        } else {
            rpincode.setError(null);
        }
        if (contact == null || contact.length() <= 10) {
            flag = false;
            rcontact.setError("Enter Correct contact");

        } else {
            rcontact.setError(null);
        }

        if (income == null  ) {
            flag = false;
            rincome.setError("Enter correct income");

        } else {
            rincome.setError(null);
        }

        if (street == null  ) {
            flag = false;
            rstreet.setError("Enter correct detail");

        } else {
            rstreet.setError(null);
        }

        if (state== null  ) {
            flag = false;
            rstate.setError("Enter correct income");

        } else {
            rstate.setError(null);
        }

        if (district == null  ) {
            flag = false;
            rdistrict.setError("Enter correct income");

        } else {
            rdistrict.setError(null);
        }

        if (qualification == null  ) {
            flag = false;
            rqualif.setError("Enter correct income");

        } else {
            rqualif.setError(null);}

        if (workindetail == null  ) {
            flag = false;
            rworkindetail.setError("Enter correct income");

        } else {
            rworkindetail.setError(null);}

        if (workinmonth == null  ) {
            flag = false;
            rworkexpinmonth.setError("Enter correct income");

        } else {
            rworkexpinmonth.setError(null);
        }

        if (preferedcourse == null  ) {
            flag = false;
            rprefcourse.setError("Enter correct income");

        } else {
            rprefcourse.setError(null);
        }

        if (preferedsector== null  ) {
            flag = false;
            rpreferedsec.setError("Enter correct income");

        } else {
            rpreferedsec.setError(null);
        }

        if (maxfees== null  ) {
            flag = false;
            rmaxfees.setError("Enter correct income");

        } else {
            rmaxfees.setError(null);
        }

        return flag;
    }
}


class register_info_data extends AsyncTask<String,Void,String> {
    boolean flag;
    registration_form rf;
    Context context;
    int gender_id;
    View v;
    public register_info_data(registration_form rf,Context context) {
      LayoutInflater  inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //v = inflater.inflate(R.layout.feedback, this, true);

        this.rf=rf;
        this.context=context;
        gender_id=this.rf.gender.getCheckedRadioButtonId();
        //rf.gender_radio=(RadioButton)

    }

    @Override
    protected void onPostExecute(String s) {


        Toast.makeText(context.getApplicationContext(),s,Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            String link="https://9a3a0b42.ngrok.io/api/candidateregister/";




            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url=new URL(link);
            URLConnection con=url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr=new OutputStreamWriter(con.getOutputStream());


            JSONObject add=new JSONObject();

            add.put("c_name",rf.name);
            add.put("c_email_id",rf.mail);
            add.put("c_alternate_email_id","");
            //add.put("c_gender",)

            add.put("rating",3);






            wr.write(add.toString());
            wr.flush();




            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb=new StringBuilder();
            String line=null;

            while ((line=reader.readLine())!=null)
            {
                Log.d("LINE : ",line);


                sb.append(line);
            }
            return sb.toString();



        } catch (Exception e) {
            Log.d("ERROR",e.getMessage());
            return "Exception: " + e.getMessage();
        }

    }
}
