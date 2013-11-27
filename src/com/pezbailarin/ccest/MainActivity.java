package com.pezbailarin.ccest;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.util.*;

public class MainActivity extends Activity
{
	Spinner spinnerPaises;
	Spinner spinnerYears;
	ArrayList<String> items=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		String[] paises={"Germany","Russia", "USA","Italy","France", "UK-Commonwealth"};
		ArrayAdapter<String> adapterP=new ArrayAdapter<String>(this, 
			android.R.layout.simple_spinner_dropdown_item,paises);
		spinnerPaises=(Spinner)findViewById(R.id.spinner1);
		spinnerPaises.setAdapter(adapterP);
		
		String[] year={"1939","1940","1941","1942","1943","1944","1945"};
		ArrayAdapter<String> adapterY=new ArrayAdapter<String>(this, 
			android.R.layout.simple_spinner_dropdown_item,year);
		spinnerYears=(Spinner)findViewById(R.id.spinner2);
		spinnerYears.setAdapter(adapterY);
		
		ListView lista=(ListView)findViewById(R.id.listView);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		lista.setAdapter(adapter);
		
    }
	
	
	public void mostrar(View v){
		items.clear();
		
	//	Toast.makeText(this,"es el ",Toast.LENGTH_LONG).show();
		int pais=spinnerPaises.getSelectedItemPosition();
		int anyo=spinnerYears.getSelectedItemPosition();
		EditText et=(EditText)findViewById(R.id.editText);
		int dado=  Integer.parseInt(et.getText().toString())-2;
		
		for(int i=0;i<(SupportTable[pais][dado][anyo].length);i++){
			if(SupportTable[pais][dado][anyo][i]>0) {
				Toast.makeText(this,"si "+SupportUnits[pais][i],Toast.LENGTH_SHORT).show();
				items.add(SupportUnits[pais][i]);
			}
		}
		
		adapter.notifyDataSetChanged();
	//	Log.d(getPackageName(), Integer.toString(i));}
	//	catch (Exception e) {
	//		Toast.makeText(this, "es el error "+e, Toast.LENGTH_LONG).show();
	//	}
	//	Toast.makeText(this,"es el "+i,Toast.LENGTH_LONG).show();
	}
	
	
	private String[][] SupportUnits ={
	{"Cpt Wehling", "Lt Borbe", "Lt Hutzinger/Bolter", "Sgt Benzing/Grein", "Sgt Pfeiffer", "Cpl Schmidt", "Cpl Guttman", "Wpn Team + LMG", "Wpn Team + HMG", "Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + IG 18", "Wpn Team + IG 33", "Pioneer + Flamethrower", "Pioneer + Satchel Charge", "SS Squad", "Elite Rifle Squad", "Parachute/Sturm Squad", "Rifle Squad", "Volksgrenadier Squad", "Conscript Squad", "Radio: 150mm", "Radio: 120mm", "Radio: 105mm", "Radio: 81mm", "Radio: 75mm"},
	{"Cpt Antonile", "Lt Romero", "Lt Zanella", "Sgt Minutello", "Sgt Ruggiero", "Sgt Carboni", "Cpl Pagliari", "Cpl Farinato", "Cpl Castania", "Wpn Team + LMG","Wpn Team + MMG", "Wpn Team + HMG", "Wpn Team + Brixia Mortar", "Wpn Team + Medium Mortar", "Wpn Team + Mountain Gun","Gustatori + Flamethrower", "Guastatori + Satchel Charge", "Elite Team + Mol. cocktail", "Sissi Squad", "Bersaglieri Squad", "Fucilieri Squad", "Blackshirt Squad", "Radio: 150mm",  "Radio: 105mm", "Radio: 81mm","Radio: 75mm"},
	{"Cpt Gough", "Lt Alier", "Lt Levasseur", "Sgt Fache", "Sgt Delvoie", "Sgt Picard", "Sgt Vernejout", "Cpl Besson", "Cpl Benoit", "Wpn Team + LMG", "Wpn Team + HMG", "Wpn Team + .50cal (US) MG","Wpn Team + 50mm Mortar","Wpn Team + 60mm Mortar","Wpn Team + Medium Mortar", "Wpn Team + French 75","Elite Team + Satchel Charge", "BAR Squad", "Legionnaire Squad", "Chasseur Squad", "Reservist Squad", "Radio: 150mm",  "Radio: 105mm", "Radio: 81mm", "Radio: 75mm" },
	{"Cpt Iggleby", "Lt Dan", "Lt Wallace", "Lt O'Malley", "Sgt Kwan", "Sgt Foley", "Sgt Crowe", "Cpl Isway", "Cpl Cork", "Wpn Team + LMG", "Wpn Team + HMG", "Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + 25 Pounder", "Engineer + Flamethrower", "Engineer + Satchel Charge", "Airborne Squad", "Guards Squad", "Line Squad", "Territorial Squad", "Radio: 183mm", "Radio: 152mm", "Radio: 140mm", "Radio: 114mm", "Radio: 88mm", "Radio: 76mm" },
	{"Cpt Egorov", "Lt Ostroumov", "Lt Bijak", "Sgt Pyotor", "Sgt Rodimtsev", "Sgt Kaminsky", "Cpl Anishchik", "Cpl Kutikov", "Cpl Denikin", "Wpn Team + LMG", "Wpn Team + MMG", "Wpn Team + HMG", "Wpn Team + .50cal MG","Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + Infantry Gun", "Assault + Flamethrower", "Assault + Satchel Charge", "Guards Rifle Squad", "Guards SMG Squad", "Rifle Squad", "SMG Squad", "Militia Squad", "Radio: 152mm",  "Radio: 122mm", "Radio: 82mm", "Radio: 76mm" },
	{"Cpt Sitner","Lt Esparza", "Lt Thomas", "Sgt Bergstrom", "Sgt Fuller", "Sgt Elkheart", "Sgt Goziak", "Cpl Jensen", "Cpl Twells", "Wpn Team + MMG", "Wpn Team + HMG","Wpn Team + .50cal MG","Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + Pak Howitzer", "Engineer + Flamethrower", "Engineer + Satchel Charge", "Paratroop Squad", "Elite Squad", "Line Squad", "Green Squad", "Radio: 203mm", "Radio: 155mm", "Radio: 105mm", "Radio: 81mm", "Radio: 75mm"}
	};
	
	private int[][][][] SupportTable= {
		//Aleman
		{
			//2
			{
				//1939
				{ 0,0,0,0,0,0,3, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,0,0,0,0 },
				//1940...
				{ 0,0,0,0,0,0,3, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,3, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,3, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,4,0,0,0 },
				{ 0,0,0,0,0,0,3, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,4,0,0,0 },
				{ 0,0,0,0,0,0,3, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,0,0,0,0 }
			},
			//3
			{
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,0,1, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 0,3,0,0,0,0,0,0, 0,0,0,0,0,1, 0,4,0,0,0 },
				{ 0,0,0,0,0,0,3, 0,3,0,0,0,0,0,0, 0,0,0,0,0,1, 0,4,0,0,0 }
			},
			//4
			{
				{ 0,0,0,0,0,3,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,3,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,3,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,3,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 2,0,2,0,0,0,0,0, 0,0,0,0,1,0, 0,0,4,0,0 },
				{ 0,0,0,0,0,0,0, 2,0,2,0,0,0,0,0, 0,0,0,0,0,1, 0,0,4,0,0 }
			},
			//5
			{
				{ 0,0,0,0,4,0,0, 0,0,0,2,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 0,0,0,2,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 0,0,0,2,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 0,0,0,2,0,0,0,0, 0,0,0,1,0,0, 0,0,4,0,0 },
				{ 0,0,0,0,0,3,0, 0,0,2,0,0,0,0,0, 0,0,0,0,1,0, 0,0,4,0,0 },
				{ 0,0,0,0,0,3,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 }
			},
			// 6
			{
				{ 0,0,0,0,4,0,0, 0,3,0,0,0,0,0,0, 0,0,0,1,0,0, 0,0,4,0,0 },
				{ 0,0,0,0,4,0,0, 0,3,0,0,0,0,0,0, 0,0,0,1,0,0, 0,0,4,0,0 },
				{ 0,0,0,0,4,0,0, 0,3,0,0,0,0,0,0, 0,0,0,1,0,0, 0,0,4,0,0 },
				{ 0,0,0,0,4,0,0, 0,3,0,0,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 0,3,0,0,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 0,3,0,0,0,0,0,0, 0,0,0,1,0,0, 0,0,0,0,0 },
				{ 0,0,0,0,0,3,0, 0,3,0,0,0,0,0,0, 0,0,0,0,1,0, 0,0,0,0,0 }
			},
			// 7
			{
				{ 0,0,0,5,0,0,0, 2,0,2,0,0,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 },
				{ 0,0,0,5,0,0,0, 2,0,2,0,0,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 },
				{ 0,0,0,5,0,0,0, 2,0,2,0,0,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 },
				{ 0,0,0,5,0,0,0, 2,0,2,0,0,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 },
				{ 0,0,0,0,4,0,0, 2,0,0,2,0,0,0,0, 0,0,0,1,0,0, 0,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 2,0,0,2,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 },
				{ 0,0,0,0,4,0,0, 2,0,0,2,0,0,0,0, 0,0,0,1,0,0, 5,0,0,0,0 }
			},
			// 8
			{
				{ 0,0,0,5,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,2,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 },
				{ 0,0,0,5,0,0,0, 0,3,0,0,2,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 },
				{ 0,0,0,0,4,0,0, 0,3,0,0,2,0,0,0, 0,0,0,1,0,0, 0,0,0,3,0 }
			},
			// 9
			{
				{ 0,0,5,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,5,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,5,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,5,0,0,0,0, 0,3,0,0,0,0,0,0, 2,0,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,3,0,0,0,0,0,0, 0,0,2,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,0,0,0,0, 0,0,2,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,1,0,0, 0,0,0,0,0 }
			},
			// 10
			{
				{ 0,6,0,0,0,0,0, 0,3,0,0,0,0,0,0, 0,2,0,0,0,0, 0,0,0,0,3 },
				{ 0,6,0,0,0,0,0, 0,3,0,0,0,0,0,0, 0,2,0,0,0,0, 0,0,0,0,3 },
				{ 0,6,0,0,0,0,0, 0,3,0,0,0,0,0,0, 0,2,0,0,0,0, 0,0,0,0,3 },
				{ 0,6,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,2,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,5,0,0,0,0, 0,0,0,0,0,0,0,0, 2,0,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,5,0,0,0,0, 0,0,0,0,0,0,0,0, 2,0,0,0,0,0, 0,0,0,0,0 },
				{ 0,0,0,5,0,0,0, 0,0,0,0,0,0,0,0, 0,0,2,0,0,1, 0,0,0,0,0 }
			},
			// 11
			{
				{ 6,0,0,0,0,0,0, 0,0,0,0,0,3,0,0, 0,2,0,0,0,0, 0,0,0,0,0 },
				{ 6,0,0,0,0,0,0, 0,0,0,0,0,3,0,2, 0,2,0,0,0,0, 0,0,0,0,0 },
				{ 6,0,0,0,0,0,0, 0,0,0,0,0,3,0,2, 0,2,0,0,0,0, 0,0,0,0,0 },
				{ 6,0,0,0,0,0,0, 0,0,0,0,0,3,0,2, 0,2,0,0,0,0, 0,0,0,0,3 },
				{ 0,6,0,0,0,0,0, 0,0,0,0,0,3,0,2, 0,2,0,0,0,0, 0,0,0,0,3 },
				{ 0,6,0,0,0,0,0, 0,0,0,0,0,3,0,2, 0,2,0,0,0,1, 0,0,0,0,3 },
				{ 0,0,5,0,0,0,0, 0,0,0,0,0,3,0,2, 2,0,0,0,0,1, 0,0,0,0,3 }
			},
			// 12
			{
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,2,0,0,0,0, 0,0,0,3,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 0,2,0,0,0,0, 0,0,0,3,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 0,2,0,0,0,0, 0,0,0,3,0 },
				{ 0,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 0,2,0,0,0,0, 0,0,0,0,0 },
				{ 6,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 0,2,0,0,0,1, 0,0,0,0,0 },
				{ 6,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 0,2,0,0,0,1, 0,0,0,0,0 },
				{ 0,6,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,2,0,0,0,1, 0,0,0,0,0 }
			}
		}
	//Italiano
    // 2
	{
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 3,0,0,0  },  //ojo a los finlandeses
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  }
	},
	// 3
	{
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,0,2,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  }
	},
	// 4
	{
        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  }
	}, 
	// 5
	{
        { 0,0,0,0,0,0,0,0,2, 0,2,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  }
	},

	// 6
	{
        { 0,0,0,0,0,0,0,2,0, 1,0,0,1,0,0,0,0,0, 2,0,0,0, 0,0,2,0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,0,1,0,0,0,0,0, 2,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,0,1,0,0,0,0,0, 2,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,0,1,0,0,0,0,0, 0,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0, 0,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0, 0,0,1,0, 0,0,0,0  }
	},

	// 7
	{
        { 0,0,0,0,3,0,2,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,2  },
        { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 2,0,1,0, 0,0,0,2  },
        { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 2,0,1,0, 0,0,0,2  },
        { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 2,0,1,0, 0,0,0,2  },
        { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2  },
        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2  }
	},
	// 8
	{
        { 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,0,0, 0,0,2,0  },
        { 0,0,0,0,2,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,1,0, 0,0,2,0  },
        { 0,0,0,0,2,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,1,0, 0,0,2,0  },
        { 0,0,0,0,2,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,1,0, 0,0,2,0  },
        { 0,0,0,0,0,0,2,0,0, 0,0,0,0,2,0,0,0,1, 0,0,1,0, 0,0,2,0  },
        { 0,0,0,0,0,0,2,0,0, 0,0,0,0,2,0,0,0,1, 0,0,1,0, 0,0,2,0  }
	},
    // 9
	{
        { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,0,0,0, 0,0,0,0  },
        { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,0,1,0, 0,3,0,0  },
        { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,0,1,0, 0,3,0,0  },
        { 0,0,0,2,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,0,1,0, 0,3,0,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,1,0, 0,3,0,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,1,0, 0,3,0,0  }
	},
	// 10
	{
        { 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
        { 0,0,2,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,1,0,0, 0,0,0,0  },
        { 0,0,2,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,1,0,0, 0,0,0,0  },
        { 0,0,2,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,1,0,0, 0,0,0,0  },
        { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,1,0,0, 0,0,0,0  },
        { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0  }
	},
	// 11
	{ { 4,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
        { 0,0,2,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
        { 0,0,2,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,1,0,0, 0,0,0,2  }
	},
	// 12
	{
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,0,0,0, 0,3,0,0  },
        { 3,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
        { 3,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
        { 3,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  }
	}
	},
	{//Francés
		{
			{ 0,0,0,0,0,0,0,0,2, 0,2,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0 }, //ojo con las unidades sólo polacas o solo belgas o solo francesas
			{ 0,0,0,0,0,0,0,0,2, 0,2,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0, 0,0,0,1, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 }
		},
		{
			{ 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,0,1, 0,0,3,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,0,1, 0,0,3,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,0,1, 0,0,3,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0, 0,0,3,0 },
			{ 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,3,0 },
			{ 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,3,0 },
			{ 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,3,0 }
		},
		{ //4
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,1, 0,3,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,1, 0,3,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,1, 0,3,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0, 0,3,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,3,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 }
		},
		{ // 5
			{ 0,0,0,0,0,0,0,2,0, 0,2,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,2,0, 1,0,0,0,0,0,0,0, 0,0,1,0, 4,0,0,0 },
			{ 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 }
		},
		{ // 6
			{ 0,0,0,0,0,0,3,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,3,0,0, 1,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,3,0,0, 1,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,3,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,3,0,0, 0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,3,0,0, 0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,3,0,0, 0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0 }
		},
		{ // 7
			{ 0,0,0,0,0,3,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,2 },
			{ 0,0,0,0,0,3,0,0,0, 1,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,2 },
			{ 0,0,0,0,0,3,0,0,0, 1,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,2 },
			{ 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,3,0,0,0,0, 1,2,0,0,0,0,0,0, 0,0,1,0, 0,0,3,0 },
			{ 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,1,0, 0,3,0,0 },
			{ 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,1,0, 0,3,0,0 }
		},
		{ // 8
			{ 0,0,0,0,3,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0 },
			{ 0,0,0,0,3,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,2 },
			{ 0,0,0,3,0,0,0,0,0, 0,0,0,0,2,0,0,0, 0,1,0,0, 0,0,0,2 },
			{ 0,0,0,3,0,0,0,0,0, 0,0,0,0,2,0,0,0, 1,0,0,0, 0,0,0,0 },
			{ 0,0,0,3,0,0,0,0,0, 0,0,0,0,2,0,0,0, 1,0,0,0, 0,0,0,0 }
		},
		{ // 9
			{ 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,2,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,3,0,0,0,0,0, 0,0,0,0,2,0,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 1,0,0,0, 0,0,0,0 },
			{ 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 1,0,0,0, 0,0,0,0 }
		},
		{ // 10
			{ 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,0,0, 0,0,0,2 },
			{ 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,2,2,0, 0,1,0,0, 0,0,0,2 },
			{ 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,2,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,2,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,2,0,0, 0,0,0,0, 0,0,3,0 },
			{ 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,2,0,0, 1,0,0,0, 0,0,3,0 },
			{ 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,2,0,0, 1,0,0,0, 0,0,3,0 }
		}
		{ // 11
			{ 0,4,0,0,0,0,0,0,0, 0,0,0,2,0,2,0,1, 0,0,0,0, 0,0,0,0 },
			{ 0,4,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,1, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,1, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0, 0,0,0,0 },
			{ 4,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,1, 0,0,0,0, 0,0,0,0 },
			{ 4,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,1, 0,0,0,0, 0,0,0,2 },
			{ 4,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,1, 0,0,0,0, 0,0,0,2 }
		},
		{ // 12
			{ 4,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 1,0,0,0, 0,0,0,0 },
			{ 4,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 1,0,0,0, 0,0,0,0 },
			{ 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0, 0,0,0,0, 0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0, 0,0,0,0, 0,0,0,0 }
		},
	},{ //Gran Bretaña y Commonwealth
     { // 2
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,2,0,0,0,0, 0,0,0,1, 6,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0, 0,0,0,1, 6,0,0,0,0,0  }
      },
     { // 3
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,3, 0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,1, 0,5,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,1, 0,5,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0, 0,0,0,1, 0,5,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0, 0,0,0,1, 0,5,0,0,0,0  }
      },
     { // 4
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,0,1,0, 0,5,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,0,1,0, 0,5,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,0,0,1, 0,0,5,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,0,0,1, 0,0,5,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,0,0,1, 0,0,5,0,0,0  },
        { 0,0,0,0,0,0,0,3,0, 0,2,0,0,0,0,0, 0,0,0,1, 0,0,5,0,0,0  }
      },
     { // 5
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0, 0,0,1,0, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0, 0,0,1,0, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,0,3,0, 0,0,2,0,0,0,0, 0,0,1,0, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,0,3,0, 0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,0,3,0, 0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,4,0,0, 0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,4,0  }
      },
     { // 6
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,3,0, 0,2,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,0,3,0, 0,2,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,4,0,0, 0,2,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,0,4,0,0, 0,2,0,0,0,0,0, 0,0,1,0, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,4,0,0, 0,2,0,0,0,0,0, 0,0,0,1, 0,0,0,0,4,0  },
        { 0,0,0,0,0,0,4,0,0, 0,2,0,0,0,0,0, 0,0,0,1, 0,0,0,0,4,0  }
      },
     { // 7
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,0,1,0, 0,0,0,5,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,0,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,5,0,0,0,0, 2,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0,0  }
      },
     { // 8
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,1,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,0,4,0,0,0, 2,0,0,0,0,0,0, 0,1,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,5,0,0,0,0, 2,0,0,0,0,0,0, 0,1,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,5,0,0,0,0, 2,0,0,0,0,0,0, 0,1,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,5,0,0,0,0, 2,0,0,0,0,0,0, 0,1,1,0, 0,0,0,0,0,0  },
        { 0,0,0,0,5,0,0,0,0, 2,0,0,0,0,0,0, 0,1,1,0, 0,0,0,0,0,0  }
      },
     { // 9
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0,0,0  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,5,0,0  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,2,0,0,0, 0,1,0,0, 0,0,0,0,0,3  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,2,0,0,0, 0,1,0,0, 0,0,0,0,0,3  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,2,0,0,0, 0,1,0,0, 0,0,0,0,0,3  },
        { 0,0,0,0,5,0,0,0,0, 0,0,0,2,0,0,0, 0,1,0,0, 0,0,0,0,0,3  }
      },
     { // 10
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,4,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,1,0,0, 0,0,0,0,0,0  },
        { 0,0,4,0,0,0,0,0,0, 0,2,0,2,0,0,0, 0,1,0,0, 0,0,0,0,0,0  },
        { 0,0,0,0,5,0,0,0,0, 0,2,0,0,2,0,0, 0,1,0,0, 0,0,0,5,0,0  },
        { 0,0,0,0,5,0,0,0,0, 0,2,0,0,0,0,0, 0,1,0,0, 0,0,0,5,0,0  },
        { 0,0,0,0,5,0,0,0,0, 0,2,0,0,0,0,0, 0,1,0,0, 0,0,0,5,0,0  },
        { 0,5,0,0,0,0,0,0,0, 0,2,0,0,0,0,0, 0,1,0,0, 0,0,0,0,0,0  }
      },
     { // 11
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,4,0,0,0,0,0,0, 0,0,0,2,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,0,4,0,0,0,0,0,0, 0,0,0,0,0,0,2, 2,0,0,0, 0,0,0,0,0,0  },
        { 0,5,0,0,0,0,0,0,0, 0,0,0,0,0,0,2, 2,0,0,0, 0,0,0,0,0,0  },
        { 0,5,0,0,0,0,0,0,0, 0,0,0,0,2,0,2, 2,0,0,0, 0,0,0,0,0,0  },
        { 0,5,0,0,0,0,0,0,0, 0,0,0,0,2,0,2, 2,0,0,0, 0,0,0,0,0,0  },
        { 6,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,2, 2,0,0,0, 0,0,0,5,0,0  }
      },
     { // 12
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,5,0,0,0,0,0,0,0, 0,0,0,0,2,0,0, 0,0,0,0, 0,0,0,0,0,0  },
        { 0,5,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0,0,3  },
        { 6,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0,0,0  },
        { 6,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0,0,0  },
        { 6,0,0,0,0,0,0,0,0, 0,0,0,0,0,3,0, 2,0,0,0, 0,0,0,0,0,0  },
        { 6,0,0,0,0,0,0,0,0, 0,0,0,0,0,3,0, 2,0,0,0, 0,0,0,0,0,0  }
      }
   },
  
   { //URSS
     { // 2
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 4,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 4,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 2,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 2,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  }
      },
     { // 3
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 4,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 4,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,0,1,0, 0,0,0,0  }
      },
     { // 4
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,0,0,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,0,1,0, 4,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,0,1,0, 4,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0,0, 0,0,0,1,0, 4,0,0,0  }
      },
     { // 5
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 0,4,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 0,4,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,0,0,1, 0,4,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,2,0,0, 0,0,0,0,2,0,0,0,0, 0,0,0,1,0, 0,0,0,0  },
        { 0,0,0,0,0,0,2,0,0, 0,0,0,0,2,0,0,0,0, 0,0,1,0,0, 0,0,0,0  }
      },
     { // 6
        { 0,0,0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,0,0,0  },
        { 0,0,0,0,0,0,0,2,0, 2,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,4,0,0  },
        { 0,0,0,0,0,0,0,2,0, 2,0,0,0,0,0,0,0,0, 0,0,1,0,1, 0,4,0,0  },
        { 0,0,0,0,0,2,0,0,0, 2,0,0,0,0,0,0,0,0, 0,0,1,0,1, 0,4,0,0  },
        { 0,0,0,0,0,2,0,0,0, 2,0,0,0,0,0,0,0,0, 0,0,1,0,1, 0,4,0,0  }
      },
     { // 7
        { 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,0,3,0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,0,3,0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,0,0,0,0,0,0,0, 0,0,0,1,1, 0,0,3,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,1,0,1, 0,0,3,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,2,0,0,0, 0,0,1,0,1, 0,0,3,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,2,0,0,0, 0,0,1,0,1, 0,0,3,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,2,0,0,0, 0,0,1,0,1, 0,0,3,0  }
      },
      { // 8
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,2,0,0, 0,0,1,0,0, 0,0,0,2  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,2,0,0, 0,0,1,0,0, 0,0,0,2  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,2,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,2,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,2,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,3,0,0,0,0,0, 0,0,2,0,0,0,2,0,0, 0,2,0,0,1, 0,0,0,0  }
      },
     { // 9
        { 0,0,0,0,0,3,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,0,0,3,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,0,0,3,0,0,0, 0,0,0,0,2,0,0,0,0, 0,0,1,0,0, 0,0,0,2  },
        { 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,1,0,0, 0,0,0,2  },
        { 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,2,0,0,1, 0,0,0,0  },
        { 0,0,3,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,2,0,0,1, 0,0,0,0  },
        { 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0,0, 2,0,0,0,1, 0,0,0,0  }
      },
     { // 10
        { 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0,0, 0,0,1,0,0, 0,0,0,0  },
        { 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0,0, 0,2,0,0,1, 0,0,0,0  },
        { 0,0,3,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 2,0,0,0,1, 0,0,0,2  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0,1, 0,0,0,2  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0,1, 0,0,0,2  }
      },
     { // 11
        { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,2, 0,2,0,0,1, 0,0,3,0  },
        { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,2, 0,2,0,0,1, 0,0,3,0  },
        { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,2, 0,2,0,0,1, 0,0,3,0  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,2, 2,0,0,0,1, 0,0,3,0  },
        { 0,3,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0,2, 2,0,0,0,1, 0,0,3,0  },
        { 4,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0,2, 2,0,0,0,1, 0,0,3,0  },
        { 4,0,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,0,2, 2,0,0,0,1, 0,0,3,0  }
      },
     { // 12
        { 0,3,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,0,0, 1,0,0,0,0, 0,0,0,0  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,0,0, 1,0,0,0,0, 0,0,0,0  },
        { 0,3,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,0,0, 1,0,0,0,0, 0,0,0,0  },
        { 4,0,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,3,0, 1,0,0,0,0, 0,0,0,0  },
        { 4,0,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,3,0, 1,0,0,0,0, 0,0,0,0  },
        { 4,0,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,3,0, 1,0,0,0,0, 0,0,0,0  },
        { 4,0,0,0,0,0,0,3,0, 0,0,0,0,0,0,0,3,0, 1,0,0,0,0, 0,0,0,0  }
      }
  },
  { //USA
     { // 2
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,2,0,0,0,0, 0,0,0,1, 7,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,0  }
      },    
     { // 3
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,2, 0,0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,0,2,0,0,0,0, 0,0,0,1, 7,0,0,0,0  }
      },
     { // 4
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,0,0,0,2, 2,0,0,0,0,0,0,0, 0,0,0,1, 0,7,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 2,0,0,0,0,0,0,0, 0,0,0,1, 0,7,0,0,0  },
        { 0,0,0,0,0,0,0,2,0, 2,0,0,0,0,0,0,0, 0,0,0,1, 0,7,0,0,0  }
      },
     { // 5
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,2,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0  },
        { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0, 0,0,0,1, 0,0,0,0,0  },
        { 0,0,0,0,0,0,3,0,0, 0,0,2,0,0,0,0,0, 0,0,2,0, 0,0,0,0,0  }
      },
     { // 6
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,0,0,2,0, 0,0,0,2,0,0,0,0, 0,0,0,1, 0,0,0,0,0  },
        { 0,0,0,0,0,3,0,0,0, 0,0,0,2,0,0,0,0, 0,0,2,0, 0,0,0,0,0  },
        { 0,0,0,0,0,3,0,0,0, 0,0,0,2,0,0,0,0, 0,0,2,0, 0,0,0,0,0  }
      },
     { // 7
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,0,3,0,0, 2,0,0,0,0,0,0,0, 0,0,2,0, 0,0,5,0,0  },
        { 0,0,0,0,0,0,3,0,0, 2,0,0,0,0,0,0,0, 0,0,2,0, 0,0,5,0,0  },
        { 0,0,0,0,4,0,0,0,0, 2,0,0,0,0,0,0,0, 0,0,2,0, 0,0,5,0,0  }
      },
     { // 8
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,0,3,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0, 0,0,0,5,0  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0, 0,0,0,5,0  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,0,2,0,0,0, 0,0,2,0, 0,0,0,5,0  }
      },
     { // 9
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,0,4,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,2,0, 0,0,0,0,0  },
        { 0,0,0,0,4,0,0,0,0, 0,2,0,0,0,0,0,0, 0,0,2,0, 0,0,0,0,0  },
        { 0,0,3,0,0,0,0,0,0, 0,2,0,0,0,0,0,0, 0,2,0,0, 0,0,0,0,0  }
      },
     { // 10
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,0,4,0,0,0,0,0, 0,0,0,0,0,2,0,0, 0,0,2,0, 0,0,0,0,0  },
        { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,2,0,0, 0,2,0,0, 0,0,0,0,0  },
        { 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,2,0,0, 0,2,0,0, 0,0,0,0,0  }
      },
     { // 11
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,0,0,2, 0,2,0,0, 0,0,0,0,4  },
        { 0,5,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2, 0,2,0,0, 0,0,0,0,4  },
        { 5,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2, 2,0,0,0, 0,0,0,0,4  }
      },
     { // 12
        { 0  },
        { 0  },
        { 0  },
        { 0  },
        { 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 0,2,0,0, 0,0,0,5,0  },
        { 5,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 2,0,0,0, 0,0,0,5,0  },
        { 5,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,3,0, 2,0,0,0, 0,0,0,5,0  }
      }
  }

	};
	
	
}
