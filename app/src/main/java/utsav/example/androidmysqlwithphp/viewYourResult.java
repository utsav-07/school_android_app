package utsav.example.androidmysqlwithphp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * This class is showing students result to students...
 *
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */

public class viewYourResult extends AppCompatActivity {
    private TextView studentname,class_sec,rollno,year,semester,math,science,ssc,hindi,english,sanskrit,computer,gk,drawing,faircopy,project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_result);

        studentname        = (TextView)findViewById(R.id.studentname);
        class_sec          = (TextView)findViewById(R.id.class_section);
        rollno             = (TextView)findViewById(R.id.rollno);
        year               = (TextView)findViewById(R.id.year);
        semester           = (TextView)findViewById(R.id.semester);
        math               = (TextView)findViewById(R.id.math);
        science            = (TextView)findViewById(R.id.science);
        ssc                = (TextView)findViewById(R.id.ssc);
        hindi              = (TextView)findViewById(R.id.hindi);
        english            = (TextView)findViewById(R.id.english);
        sanskrit           = (TextView)findViewById(R.id.sanskrit);
        computer           = (TextView)findViewById(R.id.computer);
        gk                 = (TextView)findViewById(R.id.gk);
        drawing            = (TextView)findViewById(R.id.drawing);
        faircopy           = (TextView)findViewById(R.id.faircopy);
        project            = (TextView)findViewById(R.id.project);


        studentname.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getStudentname());
        class_sec.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getclass_sec());
        rollno.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getrollno());
        year.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getyear());
        semester.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getsemester());
        math.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getmath());
        science.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getscience());
        hindi.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).gethindi());
        ssc.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getssc());
        english.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getenglish());
        sanskrit.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getsanskrit());
        computer.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getcomputer());
        gk.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getgk());
        drawing.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getdrawing());
        faircopy.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getfaircopy());
        project.setText(SharedPrefManager_StudentsResultView.getInstanceviewresult(this).getproject());

    }
}
