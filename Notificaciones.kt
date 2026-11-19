package com.ebc.notificacionesdemo

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

object Notificaciones {
    private const val CANAL_ID = "notificaciones_demo"

    fun crearCanal(context: Context) {

        //Build.VERSION.SDK.INT -> Versión de mi sistema operativo
        //Build.VERSION_CODES.0 -> Android 8.0
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val canal = NotificationChannel(
                CANAL_ID,
                "Canal default de mi Apicación",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Esta es una práctica de notificaciones muy importantes!"
            }

            val manager = context.getSystemService(
                NotificationManager::class.java
            )

            manager.createNotificationChannel(canal)
        }

    }

    fun permitidas(context: Context) : Boolean {

        //Notificaciones habilitadas y la version de Android es la 8 o superior
        if(NotificationManagerCompat.from(context).areNotificationsEnabled() &&
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            ) {

                val tengoPermiso = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                return tengoPermiso
        }

        return false
    }

    private fun plantilla(
        context: Context,
        icono: Int,
        titulo: String,
        mensaje: String
    ) : NotificationCompat.Builder =
        NotificationCompat.Builder(context, CANAL_ID)
            .setSmallIcon(icono)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(
                NotificationCompat.PRIORITY_DEFAULT
            )
            .setAutoCancel(true)


    //Enviamos la notificacion
    //Debo recibir una notificacion armada.
    @SuppressLint("MissingPermission")
    private fun publicar(
        context: Context,
        id: Int,
        notification: Notification) {

        if ( !permitidas(context)) return

        NotificationManagerCompat
            .from(context)
            .notify(id, notification)
    }

    fun enviarBasica(context: Context) {
        val notificacionPlantilla =
            plantilla(
            context = context,
            icono = R.drawable.ic_stat_bell,
            titulo = "¡Hola mundo notificaciones!",
            mensaje = "Esta es mi primer notificación"
            ).build()

        publicar(context, 101, notificacionPlantilla)
    }

    fun enviarTextoLargo(context: Context) {

        val TextoLargo = "Lorem ipsum dolor sit amet," +
                "consectetur adipiscing elit. Morbi laoreet ante quis nisi semper molestie. Interdum et malesuada " +
                "fames ac ante ipsum primis in faucibus. Integer " +
                "malesuada nulla eget condimentum pharetra. Mauris " +
                "vulputate ante ut neque maximus porttitor. Pellentesque libero est, hendrerit non magna at, dapibus cursus ipsum. Maecenas sollicitudin lectus quam, ac lobortis magna convallis hendrerit. Mauris maximus iaculis tortor id vulputate. Phasellus fermentum congue neque sed pulvinar. Praesent in venenatis purus. Cras tempus, turpis et iaculis sollicitudin, enim dolor volutpat felis, in malesuada ligula velit at lorem. Proin sapien risus, ornare sit amet est sit amet, vulputate efficitur tellus. Nunc vitae tincidunt lorem. Morbi id purus laoreet, tincidunt metus vel, maximus justo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; "


        val notificacionPlantilla =
            plantilla(
                context = context,
                icono = R.drawable.ic_stat_text,
                titulo = "¡Hola mundo texto largo!",
                mensaje = "Esta es mi primer notificación de texto largo"
            )
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(TextoLargo)
                )
                .build()

        publicar(context, 102, notificacionPlantilla)
    }

    fun enviarConImagen(context: Context) {

        val gatito = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.gatito
        ) ?: return

        val notificacionPlantilla =
            plantilla(
                context = context,
                icono = R.drawable.ic_stat_image,
                titulo = "¡Hola mundo notificaciones!",
                mensaje = "Esta es mi primer notificación"
            )
                .setLargeIcon(
                    gatito
                )
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(gatito)
                )
                .build()

        publicar(context, 103, notificacionPlantilla)
    }
}