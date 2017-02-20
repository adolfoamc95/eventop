package com.example.pc02.watshappening;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc02 on 15/11/2016.
 */

public class BBDDLocal extends SQLiteOpenHelper{

    String createAcontecimientoSentence = "CREATE TABLE `acontecimientos` (" +
            "  `id` int(11) NOT NULL," +
            "  `nombre` varchar(256) NOT NULL," +
            "  `organizador` varchar(256) DEFAULT NULL," +
            "  `descripcion` varchar(1024) NOT NULL," +
            "  `tipo` int(11) NOT NULL," +
            "  `portada` varchar(256) DEFAULT NULL," +
            "  `inicio` datetime NOT NULL," +
            "  `fin` datetime NOT NULL," +
            "  `direccion` varchar(256) DEFAULT NULL," +
            "  `localidad` varchar(256) DEFAULT NULL," +
            "  `cod_postal` int(5) DEFAULT NULL," +
            "  `provincia` varchar(256) DEFAULT NULL," +
            "  `latitud` float DEFAULT NULL," +
            "  `longitud` float DEFAULT NULL," +
            "  `telefono` int(9) DEFAULT NULL," +
            "  `email` varchar(256) DEFAULT NULL," +
            "  `web` varchar(256) DEFAULT NULL," +
            "  `facebook` varchar(256) DEFAULT NULL," +
            "  `twitter` varchar(256) DEFAULT NULL," +
            "  `instagram` varchar(256) DEFAULT NULL" +
            ")" ;


    String createEventoSentence = "CREATE TABLE `eventos` (" +
            "  `id` int(11) NOT NULL," +
            "  `id_acontecimiento` int(11) NOT NULL," +
            "  `nombre` varchar(256) NOT NULL," +
            "  `descripcion` varchar(1024) NOT NULL," +
            "  `inicio` datetime NOT NULL," +
            "  `fin` datetime NOT NULL," +
            "  `direccion` varchar(256) DEFAULT NULL," +
            "  `localidad` varchar(256) DEFAULT NULL," +
            "  `cod_postal` int(5) DEFAULT NULL," +
            "  `provincia` varchar(256) DEFAULT NULL," +
            "  `latitud` float DEFAULT NULL," +
            "  `longitud` float DEFAULT NULL" ;
           // "FOREIGN KEY (`id_acontecimiento`) REFERENCES `acontecimientos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;"+
           // ")";

    String inserEventos ="INSERT INTO `eventos` (`id`, `id_acontecimiento`, `nombre`, `descripcion`, `inicio`, `fin`, `direccion`, `localidad`, `cod_postal`, `provincia`, `latitud`, `longitud`) VALUES\n" +
            "(1, 1, 'ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', 0, '', 0, 0),\n" +
            "(3, 3, 'evento nº 3', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '2016-10-19 10:30:00', '2016-10-21 11:30:00', '', '', 0, '', 0, 0),\n" +
            "(4, 4, 'ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', 0, '', 0, 0),\n" +
            "(6, 9, 'ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '2016-10-19 10:30:00', '2016-10-21 11:30:00', '', '', 0, '', 0, 0),\n" +
            "(7, 9, 'asdf', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', 0, '', 0, 0),\n" +
            "(8, 9, 'ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '1970-01-01 00:00:00', '2016-10-21 11:30:00', '', '', 0, '', 0, 0),\n" +
            "(9, 9, 'ABP en Educación Física. Los juegos de siempre: ¿Cómo podemos recuperar en el Colegio los juegos de nuestros mayores?', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '1970-01-01 00:00:00', '2016-10-21 11:30:00', '', '', 0, '', 0, 0),\n" +
            "(10, 9, 'asdf', 'Utilizar el Aprendizaje Basado en Proyectos para recuperar los juegos típicos de la infancia de los padres y abuelos de los alumnos del colegio', '1970-01-01 00:00:00', '2016-10-21 11:30:00', '', '', 0, '', 0, 0);";

    public BBDDLocal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(createAcontecimientoSentence);
            sqLiteDatabase.execSQL(createEventoSentence);

        }catch(Exception e){
            MyLog.e("BDLocal", "No se han podido crear las bases de datos"+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS acontecimientos");
        sqLiteDatabase.execSQL(createAcontecimientoSentence);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS eventos");
        sqLiteDatabase.execSQL(createEventoSentence);


    }
}
