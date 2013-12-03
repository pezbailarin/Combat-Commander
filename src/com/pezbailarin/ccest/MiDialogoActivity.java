package com.pezbailarin.ccest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.castorflex.android.flipimageview.library.FlipImageView;

public class MiDialogoActivity extends Activity implements FlipImageView.OnFlipListener{
	boolean girar_eje_X;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_custom);
		girar_eje_X=false;

		Intent intent = getIntent();
		String unidad = intent.getStringExtra("NOMBRE");
		TextView txt=(TextView)findViewById(R.id.txtNombreDialogo);
		txt.setText(unidad);
		
		//determino el nombre del recurso que muestra el reverso de la unidad;
		int ico=intent.getIntExtra("ICONO", R.drawable.nada);
		String nombre_recurso=getResources().getResourceEntryName(ico);
		
		//determino el subtexto
		txt=(TextView)findViewById(R.id.txtExtraDialogo);
		String subTxt=intent.getStringExtra("EXTRAS");
		txt.setText(subTxt);
		
		//determino el coste
		txt=(TextView)findViewById(R.id.txtCosteDialogo);
		txt.setText(Integer.toString(intent.getIntExtra("COSTE", 0)));
				
		//si el nombre del recurso termina en d, son dos unidades apiladas
		//el icono principal no coincide con la miniatura
		if(nombre_recurso.endsWith("d")==true) {
			nombre_recurso=nombre_recurso.substring(0, nombre_recurso.length()-1);
			//y además cambio la forma de girarlo para disimular que siguen en el mismo sitio después de girar
			girar_eje_X=true;			
		}
		
		//por ultimo tengo que comprobar si se trata de alguna de las unidades francesas que usan HMGs americanas
		if(unidad.equals("Wpn. Team + HMG") && !subTxt.isEmpty()) {
			//ufff espero que no haya ninguna unidad con el mismo nombre por error,
			//por si acaso compruebo que además tenga subtexto (no puedo usarlo para comparar puesto que tal vez
			//lo traduzca a otros idiomas)
			nombre_recurso="stf11b";
		}
		
		
		//normalmente el reverso se llama igual que el anverso pero empieza por b
		String nombre_reverso="b"+nombre_recurso;
		
		//pero si el nombre del recurso termina en r es una radio, y el reverso ya no se llama igual
		if(nombre_recurso.endsWith("r")) {
			nombre_reverso="b"+nombre_recurso.substring(0, 3)+"r";			
		}
		
		FlipImageView  imagen=(FlipImageView) findViewById(R.id.ivIconoDialogo);
		ico = getResources().getIdentifier(nombre_recurso, "drawable", this.getPackageName());
		int ico_reverso=getResources().getIdentifier(nombre_reverso,"drawable", this.getPackageName());
		imagen.setDrawable(getResources().getDrawable(ico));
		imagen.setFlippedDrawable(getResources().getDrawable(ico_reverso));
		imagen.setRotationXEnabled(girar_eje_X);
		imagen.setRotationYEnabled(true);
		
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
