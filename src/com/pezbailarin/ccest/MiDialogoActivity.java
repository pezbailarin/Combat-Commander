package com.pezbailarin.ccest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.castorflex.android.flipimageview.library.FlipImageView;

public class MiDialogoActivity extends Activity implements FlipImageView.OnFlipListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_custom2);

		Intent intent = getIntent();
		String message = intent.getStringExtra("NOMBRE");
		TextView txt=(TextView)findViewById(R.id.txtNombreDialogo);
		txt.setText(message);
		
		FlipImageView  imagen=(FlipImageView) findViewById(R.id.ivIconoDialogo);
		imagen.setDrawable(getResources().getDrawable(intent.getIntExtra("ICONO", R.drawable.nada)));
		
	//	ImageView icono=(ImageView)findViewById(R.id.ivIconoDialogo);
	//	icono.setImageResource(intent.getIntExtra("ICONO", R.drawable.nada));
		
		txt=(TextView)findViewById(R.id.txtExtraDialogo);
		txt.setText(intent.getStringExtra("EXTRAS"));
		
		txt=(TextView)findViewById(R.id.txtCosteDialogo);
		txt.setText(Integer.toString(intent.getIntExtra("COSTE", 0)));
		
		//cerrar la actividad al pulsar el botón
		Button boton=(Button)findViewById(R.id.dialogButtonOK);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();	
			}
		});
		
		
		
		
	}

	@Override
	public void onClick(FlipImageView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFlipStart(FlipImageView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFlipEnd(FlipImageView view) {
		// TODO Auto-generated method stub
		
	}

}
