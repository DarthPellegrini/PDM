package com.m78159.pdm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    private final int SELECIONAR_CONTATO = 1;
    private final int TIRAR_FOTO = 2;
    private final int ACESSAR_INTERNET = 3;
    private final int REALIZAR_LIGACAO = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void lerContatosClick(View view) {
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, SELECIONAR_CONTATO);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(intent, SELECIONAR_CONTATO);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void acessarWebClick(View view) {
        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, ACESSAR_INTERNET);
        }
        else {
            Uri uri = Uri.parse("http://www.unisc.br");
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void ligacaoClick(View view) {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REALIZAR_LIGACAO);
        }
        else {
            Intent it = new Intent(Intent.ACTION_DIAL);
            startActivity(it);
        }
    }

    public void gMaps1Click(View view){
        Uri uriGeo = Uri.parse("geo:<0>,<0>?q=Sete+de+setembro,Curitiba");
        Intent it = new Intent(Intent.ACTION_VIEW,uriGeo);
        startActivity(it);
    }

    public void gMaps2Click(View view){
        String localizacao = "geo:<-25.443195>,<-49.280977>?q=<-25.443195>,<-49.280977>";
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(localizacao)));
    }

    public void gMaps3Click(View view){
        String url = "http://maps.google.com/maps?f=d&saddr=-25.443195,-49.280977&daddr=-25.442207,-49.278403&hl=pt";
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void takePictureClick(View view){
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, TIRAR_FOTO);
        }
        else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, TIRAR_FOTO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case SELECIONAR_CONTATO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, SELECIONAR_CONTATO);
                } else
                    Toast.makeText(this, "permissão de acesso aos contatos negada", Toast.LENGTH_LONG).show();
                break;
            case TIRAR_FOTO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TIRAR_FOTO);
                } else
                    Toast.makeText(this, "permissão de acesso à câmera negada", Toast.LENGTH_LONG).show();
                break;
            case ACESSAR_INTERNET:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Uri uri = Uri.parse("http://www.unisc.br");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                } else
                    Toast.makeText(this, "permissão de acesso à internet negada", Toast.LENGTH_LONG).show();
                break;
            case REALIZAR_LIGACAO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent it = new Intent(Intent.ACTION_DIAL);
                    startActivity(it);
                } else
                    Toast.makeText(this, "permissão de realizar ligações negada", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECIONAR_CONTATO && resultCode == RESULT_OK) {
            Uri contactData = data.getData();

            Cursor phones = getContentResolver()
                    .query(contactData,null,null,null,null);

            String name = "", phoneNumber = "";

            while (phones.moveToNext()) {
                name = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));

            }
            phones.close();
            TextView tvNome = findViewById(R.id.txtNome);
            tvNome.setText("Nome: " + name);
            TextView tvPhone = findViewById(R.id.txtTelefone);
            tvPhone.setText("Telefone: " + phoneNumber);
        }
        else{
            if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
                ImageView iv = findViewById(R.id.picItiPhoto);
                iv.setImageBitmap((Bitmap) data.getExtras().get("data"));
            }
        }

    }
}
