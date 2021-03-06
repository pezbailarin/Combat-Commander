

package com.pezbailarin.ccest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {
        static final String CLAVE="IntentKey";
        ArrayList<UnitDetails> Detalles=new ArrayList<UnitDetails>();
        ListCustomAdapter miListAdapter;
        
        int i_pais=-1,i_year,i_dice;

        Spinner spinnerPaises;
        Spinner spinnerYears;
        Spinner spinnerDados;

    String[] paises;

    String[] year;
        String [] subtitulo;
        int[] icPaises={R.drawable.icb_0,R.drawable.icb_1,R.drawable.icb_2,
                        R.drawable.icb_3,R.drawable.icb_4,R.drawable.icb_5,};

    /**
     *  Al crear la actividad inicializar cosas
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //inicializo los strings ahora que se han cargado los recursos
        paises = new String[]{getString(R.string.Germany), getString(R.string.Italia), getString(R.string.Francia), getString(R.string.UK), getString(R.string.Rusia), getString(R.string.USA)};
        subtitulo=new String[]{"", getString(R.string.and_axis_minor), getString(R.string.and_allied_minors), getString(R.string.and_commonwealth), "", ""};

        //spinner para elegir pais, mostrando sus iconos
        MiSpinnerAdapter adapterP=new MiSpinnerAdapter(this,
                        android.R.layout.simple_spinner_dropdown_item,paises);
        spinnerPaises=(Spinner)findViewById(R.id.spinner1);
        spinnerPaises.setAdapter(adapterP);

        //spinner para elegir el año
        String[] year={"1939","1940","1941","1942","1943","1944","1945"};
        ArrayAdapter<String> adapterY=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,year);
        spinnerYears=(Spinner)findViewById(R.id.spinner2);
        spinnerYears.setAdapter(adapterY);
        
        //spinner para elegir la tirada de dado
        String[] dados={"2","3","4","5","6","7","8","9","10","11","12"};
        ArrayAdapter<String> adapterD=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,dados);
        spinnerDados=(Spinner)findViewById(R.id.spinner3);
                spinnerDados.setAdapter(adapterD);

        miListAdapter = new ListCustomAdapter(this,R.layout.list_item, Detalles);
        ListView lista=(ListView)findViewById(R.id.listView);
        lista.setAdapter(miListAdapter);
        miListAdapter.notifyDataSetChanged();
        
        //si hay datos guardados, es el momento de mostrarlos
        if(savedInstanceState!=null) {
        	i_pais=savedInstanceState.getInt("pais");
        	i_year=savedInstanceState.getInt("year");
        	i_dice=savedInstanceState.getInt("dice");
        	//Log.i("pezbailarin", " Datos recuperados: pais: "+i_pais+" Año: "+i_year+" Dado: "+i_dice);
        	mostrar(lista); //necesito enviar un view como parámetro, cualquiera que no sea el botón
        }
        
	    //al pulsar un item del listview abrir el dialogo de detalle;
	    lista.setOnItemClickListener(new OnItemClickListener() {
                //pongo toda la info en extras del intent que uso para abrir la nueva activity
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
                        //no quiero mostrar el diálogo cuando no hay unidades elegibles
                        if(Detalles.get(position).getCoste()>0) {

                                Intent intent=new Intent(getApplicationContext(), MiDialogoActivity.class);
                                intent.putExtra("NOMBRE", Detalles.get(position).getNombre());
                                intent.putExtra("EXTRAS", Detalles.get(position).getExtra());
                                int ico=Detalles.get(position).getIcon();
                                intent.putExtra("ICONO", ico);
                                intent.putExtra("COSTE", Detalles.get(position).getCoste());

                                startActivity(intent);
                        }
                }
        });
    }


        /**
         * determino y muestro las unidades disponibles
         */
        public void mostrar(View v){
        	//si la llamada no viene del botón y la vble i_pais no ha sido reinicializada con datos verdaderos, no hacer nada;	
        	if(v.getId()!=R.id.boton && i_pais==-1) { return;}
        		
                Detalles.clear();
                miListAdapter.notifyDataSetChanged();
                int pais, anyo, dado;
                
                //si se ha pulsado el botón uso los datos de los spinners (y además los guardo para después)
                if(v.getId()==R.id.boton) {
	                pais=i_pais=spinnerPaises.getSelectedItemPosition();
	                anyo=i_year=spinnerYears.getSelectedItemPosition();
	                dado=i_dice=spinnerDados.getSelectedItemPosition();
                } else
                //si no se ha pulsado el botón, uso los datos previamente almacenados.	
                {	
                	pais=i_pais;
                	anyo=i_year;
                	dado=i_dice;                	
                }

                for(int i=0;i<(SupportTable[pais][dado][anyo].length);i++){
                        if(SupportTable[pais][dado][anyo][i]>0) {
                                UnitDetails unidad=new UnitDetails();
                                unidad.setIcon(UnitIcons[pais][i]);
                                unidad.setExtra("");
                                //unidades finlandesas
                                if(pais==1 && (i==17 || i==18)) {unidad.setExtra(getString(R.string.only_if_playing_a_finnish_oob));}
                                //radio finlandesa
                                if(pais==1 && i==22 && dado==0) {unidad.setExtra(getString(R.string.only_if_playing_a_finnish_oob));}
                                //paises menores aliados
                                if(pais==2 && i==10 && (dado==5 || dado==7)) {
                                        //FRANCIA: Weapon Team + HMG USA
                                        unidad.setExtra(getString(R.string.use_the_american_version_of_the_indicated_weapon));
                                        unidad.setIcon(R.drawable.stf11bd);
                                }
                                if (pais==2 && i==11) {unidad.setExtra(getString(R.string.use_the_american_version_of_the_indicated_weapon));}
                                if(pais ==2 && i==12 && dado==7){unidad.setExtra(getString(R.string.belgian_only));}
                                if(pais==2 && i==12 && dado ==9){unidad.setExtra(getString(R.string.polish_only));}
                                if(pais==2 && i==13 && dado ==9){unidad.setExtra(getString(R.string.french_only));}
                                unidad.setNombre(SupportUnits[pais][i]);
                                unidad.setCoste(SupportTable[pais][dado][anyo][i]);
                                Detalles.add(unidad);
                                miListAdapter.notifyDataSetChanged();
                        }
                }

                //si no hay unidades elegibles, indicarlo
                if (Detalles.isEmpty()) {
                        UnitDetails unidad=new UnitDetails();
                        unidad.setIcon(R.drawable.nada);
                        unidad.setNombre(getString(R.string.ninguna_unidad_disponible));
                        Detalles.add(unidad);
                }
        }



        /**
		 * Guardo los datos de los spinners
		 */
		@Override
		protected void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("pais", i_pais);
			outState.putInt("year", i_year);
			outState.putInt("dice", i_dice);
		}



		/**
         *
         *  adaptador personal para spinner
         * @author Cesar
         *
         */
        public class MiSpinnerAdapter extends ArrayAdapter<String>{

        public MiSpinnerAdapter(Context context, int textViewResourceId,   String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.spinner_item, parent, false);

            TextView label=(TextView)row.findViewById(R.id.textPais);
            label.setText(paises[position]);
            
            TextView extra=(TextView)row.findViewById(R.id.textExtra);
            extra.setText(subtitulo[position]);

            ImageView icon=(ImageView)row.findViewById(R.id.iconoPais);
            icon.setImageResource(icPaises[position]);
            return row;
                }
        }
        
        
        //aquí empiezan los datos
        // [esta clase es un desastre, todas las cosas metidas a saco; pero la escasa magnitud del proyecto me sugiere que no es tan grave
        // si algún día crece, debería pensar en separar continente y contenido en clases distintas.]
        private String[][] SupportUnits ={
        {"Cpt Wehling", "Lt Borbe", "Lt Hutzinger or Lt. Bolter", "Sgt Benzing or Sgt. Grein", "Sgt Pfeiffer", "Cpl Schmidt", "Cpl Guttman", "Wpn Team + LMG", "Wpn Team + HMG", "Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + IG 18", "Wpn Team + IG 33", "Pioneer + Flamethrower", "Pioneer + Satchel Charge", "SS Squad", "Elite Rifle Squad", "Parachute/Sturm Squad", "Rifle Squad", "Volksgrenadier Squad", "Conscript Squad", "Radio: 150mm", "Radio: 120mm", "Radio: 105mm", "Radio: 81mm", "Radio: 75mm"},
        {"Cpt Antonile", "Lt Romero", "Lt Zanella", "Sgt Minutello", "Sgt Ruggiero", "Sgt Carboni", "Cpl Pagliari", "Cpl Farinato", "Cpl Castania", "Wpn Team + LMG","Wpn Team + MMG", "Wpn Team + HMG", "Wpn Team + Brixia Mortar", "Wpn Team + Medium Mortar", "Wpn Team + Mountain Gun","Gustatori + Flamethrower", "Guastatori + Satchel Charge", "Elite Team + Mol. cocktail", "Sissi Squad", "Bersaglieri Squad", "Fucilieri Squad", "Blackshirt Squad", "Radio: 150mm",  "Radio: 105mm", "Radio: 81mm","Radio: 75mm"},
        {"Cpt Gough", "Lt Alier", "Lt Levasseur", "Sgt Fache", "Sgt Delvoie", "Sgt Picard", "Sgt Vernejout", "Cpl Besson", "Cpl Benoit", "Wpn Team + LMG", "Wpn. Team + HMG", "Wpn Team + .50cal (US) MG","Wpn Team + 50mm Mortar","Wpn Team + 60mm Mortar","Wpn Team + Medium Mortar", "Wpn Team + French 75","Elite Team + Satchel Charge", "BAR Squad", "Legionnaire Squad", "Chasseur Squad", "Reservist Squad", "Radio: 150mm",  "Radio: 105mm", "Radio: 81mm", "Radio: 75mm" },
        {"Cpt Iggleby", "Lt Dan", "Lt Wallace", "Lt O'Malley", "Sgt Kwan", "Sgt Foley", "Sgt Crowe", "Cpl Isway", "Cpl Cork", "Wpn Team + LMG", "Wpn Team + HMG", "Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + 25 Pounder", "Engineer + Flamethrower", "Engineer + Satchel Charge", "Airborne Squad", "Guards Squad", "Line Squad", "Territorial Squad", "Radio: 183mm", "Radio: 152mm", "Radio: 140mm", "Radio: 114mm", "Radio: 88mm", "Radio: 76mm" },
        {"Cpt Egorov", "Lt Ostroumov", "Lt Bijak", "Sgt Pyotor", "Sgt Rodimtsev", "Sgt Kaminsky", "Cpl Anishchik", "Cpl Kutikov", "Cpl Denikin", "Wpn Team + LMG", "Wpn Team + MMG", "Wpn Team + HMG", "Wpn Team + .50cal MG","Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + Infantry Gun", "Assault + Flamethrower", "Assault + Satchel Charge", "Guards Rifle Squad", "Guards SMG Squad", "Rifle Squad", "SMG Squad", "Militia Squad", "Radio: 152mm",  "Radio: 122mm", "Radio: 82mm", "Radio: 76mm" },
        {"Cpt Sitner","Lt Esparza", "Lt Thomas", "Sgt Bergstrom", "Sgt Fuller", "Sgt Elkheart", "Sgt Goziak", "Cpl Jensen", "Cpl Twells", "Wpn Team + MMG", "Wpn Team + HMG","Wpn Team + .50cal MG","Wpn Team + Light Mortar", "Wpn Team + Medium Mortar", "Wpn Team + Pak Howitzer", "Engineer + Flamethrower", "Engineer + Satchel Charge", "Paratroop Squad", "Elite Squad", "Line Squad", "Green Squad", "Radio: 203mm", "Radio: 155mm", "Radio: 105mm", "Radio: 81mm", "Radio: 75mm"}
        };
        
        int[][] UnitIcons = {
                {R.drawable.stg1, R.drawable.stg2, R.drawable.stg3d, R.drawable.stg4d, R.drawable.stg5, R.drawable.stg6, R.drawable.stg7, R.drawable.stg8d, R.drawable.stg9d, R.drawable.stg10d, R.drawable.stg11d, R.drawable.stg12d, R.drawable.stg13d, R.drawable.stg14d, R.drawable.stg15d, R.drawable.stg16, R.drawable.stg17, R.drawable.stg18, R.drawable.stg19, R.drawable.stg20, R.drawable.stg21, R.drawable.stg22r, R.drawable.stg23r, R.drawable.stg24r, R.drawable.stg25r, R.drawable.stg26r},
                {R.drawable.sti1, R.drawable.sti2, R.drawable.sti3, R.drawable.sti4, R.drawable.sti5, R.drawable.sti6, R.drawable.sti7, R.drawable.sti8, R.drawable.sti9, R.drawable.sti10d, R.drawable.sti11d, R.drawable.sti12d, R.drawable.sti13d, R.drawable.sti14d, R.drawable.sti15d, R.drawable.sti16d, R.drawable.sti17d, R.drawable.sti18d, R.drawable.sti19, R.drawable.sti20, R.drawable.sti21, R.drawable.sti22, R.drawable.sti23r, R.drawable.sti24r, R.drawable.sti25r, R.drawable.sti26r},
                {R.drawable.stf1, R.drawable.stf2, R.drawable.stf3, R.drawable.stf4, R.drawable.stf5, R.drawable.stf6, R.drawable.stf7, R.drawable.stf8, R.drawable.stf9, R.drawable.stf10d, R.drawable.stf11d, R.drawable.stf12d, R.drawable.stf13d, R.drawable.stf14d, R.drawable.stf15d, R.drawable.stf16d, R.drawable.stf17d, R.drawable.stf18, R.drawable.stf19, R.drawable.stf20, R.drawable.stf21, R.drawable.stf22r, R.drawable.stf23r, R.drawable.stf24r, R.drawable.stf25r},
                {R.drawable.stb1, R.drawable.stb2, R.drawable.stb3, R.drawable.stb4, R.drawable.stb5, R.drawable.stb6, R.drawable.stb7, R.drawable.stb8, R.drawable.stb9, 
                        R.drawable.stb10d, R.drawable.stb11d, R.drawable.stb12d, R.drawable.stb13d, R.drawable.stb14d, R.drawable.stb15d, R.drawable.stb16, R.drawable.stb17, 
                        R.drawable.stb18, R.drawable.stb19, R.drawable.stb20, R.drawable.stb21r, R.drawable.stb22r, R.drawable.stb23r, R.drawable.stb24r, R.drawable.stb25r, R.drawable.stb26r}, 
                {R.drawable.sts1, R.drawable.sts2, R.drawable.sts3, R.drawable.sts4, R.drawable.sts5, R.drawable.sts6, R.drawable.sts7, R.drawable.sts8, R.drawable.sts9, R.drawable.sts10d, R.drawable.sts11d, R.drawable.sts12d, R.drawable.sts13d, R.drawable.sts14d, R.drawable.sts15d, R.drawable.sts16d, R.drawable.sts17d, R.drawable.sts18d, R.drawable.sts19, R.drawable.sts20, R.drawable.sts21, R.drawable.sts22, R.drawable.sts23, R.drawable.sts24r, R.drawable.sts25r, R.drawable.sts26r, R.drawable.sts27r},
                {R.drawable.stu1, R.drawable.stu2, R.drawable.stu3, R.drawable.stu4, R.drawable.stu5, R.drawable.stu6, R.drawable.stu7, R.drawable.stu8, R.drawable.stu9, 
                        R.drawable.stu10d, R.drawable.stu11d, R.drawable.stu12d, R.drawable.stu13d, R.drawable.stu14d, R.drawable.stu15d, R.drawable.stu16d, R.drawable.stu17d, R.drawable.stu18, 
                        R.drawable.stu19, R.drawable.stu20, R.drawable.stu21, R.drawable.stu22r, R.drawable.stu23r, R.drawable.stu24r, R.drawable.stu25r, R.drawable.stu26r}};
        
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
                },
        {//Italiano
    // 2
                {
                { 0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 3,0,0,0  },  //ojo a los finlandeses
                { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,0,2,0,0,0,0,0,0, 0,0,0,1, 3,0,0,0  },
                { 0  },
                
                },
                // 3
                {
                { 0,0,0,0,0,0,0,0,0, 0,0,2,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,0,2,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0  },
                },
                // 4
                {
                { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,1, 0,2,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0  },
                }, 
                // 5
                {
                { 0,0,0,0,0,0,0,0,2, 0,2,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 0,2,0,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 2,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,0,1, 0,0,0,0  },
                { 0  },
                },
        
                // 6
                {
                { 0,0,0,0,0,0,0,2,0, 1,0,0,1,0,0,0,0,0, 2,0,0,0, 0,0,2,0  },
                { 0,0,0,0,0,0,0,2,0, 0,0,0,1,0,0,0,0,0, 2,0,1,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,2,0, 0,0,0,1,0,0,0,0,0, 2,0,1,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,2,0, 0,0,0,1,0,0,0,0,0, 0,0,1,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0, 0,0,1,0, 0,0,0,0  },
                { 0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0, 0,0,1,0, 0,0,0,0  },
                { 0  },
                },
        
                // 7
                {
                { 0,0,0,0,3,0,2,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,2  },
                { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 2,0,1,0, 0,0,0,2  },
                { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 2,0,1,0, 0,0,0,2  },
                { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 2,0,1,0, 0,0,0,2  },
                { 0,0,0,0,0,0,2,0,0, 1,0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2  },
                { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2  },
                { 0  },
                },
                // 8
                {
                { 0,0,0,3,0,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,0,0, 0,0,2,0  },
                { 0,0,0,0,2,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,1,0, 0,0,2,0  },
                { 0,0,0,0,2,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,1,0, 0,0,2,0  },
                { 0,0,0,0,2,0,0,0,0, 0,0,0,0,0,0,0,0,1, 2,0,1,0, 0,0,2,0  },
                { 0,0,0,0,0,0,2,0,0, 0,0,0,0,2,0,0,0,1, 0,0,1,0, 0,0,2,0  },
                { 0,0,0,0,0,0,2,0,0, 0,0,0,0,2,0,0,0,1, 0,0,1,0, 0,0,2,0  },
                { 0  },
                },
            // 9
                {
                { 0,0,3,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,0,0,0, 0,0,0,0  },
                { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,0,1,0, 0,3,0,0  },
                { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,0,1,0, 0,3,0,0  },
                { 0,0,0,2,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,0,1,0, 0,3,0,0  },
                { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,1,0, 0,3,0,0  },
                { 0,0,0,0,0,2,0,0,0, 0,0,0,0,0,0,0,0,0, 0,0,1,0, 0,3,0,0  },
                { 0  },
                },
                // 10
                {
                { 0,4,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
                { 0,0,2,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,1,0,0, 0,0,0,0  },
                { 0,0,2,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,1,0,0, 0,0,0,0  },
                { 0,0,2,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,1,0,0, 0,0,0,0  },
                { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,1,0,0, 0,0,0,0  },
                { 0,0,0,2,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 0,1,0,0, 0,0,0,0  },
                { 0  },
                },
                // 11
                {         { 4,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0, 2,0,0,0, 0,0,0,0  },
                { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
                { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
                { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
                { 0,0,2,0,0,0,0,0,0, 0,0,0,0,0,0,0,2,0, 2,1,0,0, 0,0,0,2  },
                { 0,0,2,0,0,0,0,0,0, 0,0,0,0,0,2,0,0,0, 2,1,0,0, 0,0,0,2  },
                { 0  },
                },
                // 12
                {
                { 0,0,0,0,0,0,0,0,0, 0,0,0,0,2,0,0,0,0, 2,0,0,0, 0,3,0,0  },
                { 3,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
                { 3,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
                { 3,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
                { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
                { 0,3,0,0,0,0,0,0,0, 0,0,0,0,0,0,2,0,0, 2,1,0,0, 4,0,0,0  },
                { 0  },
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
                        { 0,0,0,0,0,3,0,0,0, 0,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2 },
                        { 0,0,0,0,0,3,0,0,0, 1,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2 },
                        { 0,0,0,0,0,3,0,0,0, 1,0,0,0,0,0,0,0, 0,0,1,0, 0,0,0,2 },
                        { 0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0, 0,0,0,0, 0,0,0,0 },
                        { 0,0,0,0,3,0,0,0,0, 1,2,0,0,0,0,0,0, 0,1,0,0, 0,0,3,0 },
                        { 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0, 0,1,0,0, 0,3,0,0 },
                        { 0,0,0,0,3,0,0,0,0, 0,2,0,0,0,0,0,0, 0,1,0,0, 0,3,0,0 }
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
                        { 0,0,0,3,0,0,0,0,0, 0,0,0,2,0,0,0,0, 0,1,0,0, 0,0,0,0 },
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
                },
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
