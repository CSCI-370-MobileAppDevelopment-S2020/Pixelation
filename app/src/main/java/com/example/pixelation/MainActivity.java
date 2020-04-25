package com.example.pixelation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Uplode_Reg_Photo extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String KEY_User_Document1 = "doc1";
        ImageView IDProf;
        Button Upload_Btn;

        String Document_img1="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_uplode__reg__photo);

            IDProf=(ImageView)findViewById(R.id.IdProf);
            Upload_Btn=(Button)findViewById(R.id.UploadBtn);

            IDProf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectImage();

                }
            });

            Upload_Btn.setOnClickListener(this);
        }


        private void selectImage() {
            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Uplode_Reg_Photo.this);
            builder.setTitle("Add Photo!");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Take Photo"))
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                        startActivityForResult(intent, 1);
                    }
                    else if (options[item].equals("Choose from Gallery"))
                    {
                        Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                    }
                    else if (options[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                if (requestCode == 1) {
                    File f = new File(Environment.getExternalStorageDirectory().toString());
                    for (File temp : f.listFiles()) {
                        if (temp.getName().equals("temp.jpg")) {
                            f = temp;
                            break;
                        }
                    }
                    try {
                        Bitmap bitmap;
                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                        bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                        bitmap=getResizedBitmap(bitmap, 400);
                        IDProf.setImageBitmap(bitmap);
                        BitMapToString(bitmap);
                        String path = android.os.Environment
                                .getExternalStorageDirectory()
                                + File.separator
                                + "Phoenix" + File.separator + "default";
                        f.delete();
                        OutputStream outFile = null;
                        File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                        try {
                            outFile = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                            outFile.flush();
                            outFile.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == 2) {
                    Uri selectedImage = data.getData();
                    String[] filePath = { MediaStore.Images.Media.DATA };
                    Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String picturePath = c.getString(columnIndex);
                    c.close();
                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                    thumbnail=getResizedBitmap(thumbnail, 400);
                    Log.w("path of image from gallery......******************.........", picturePath+"");
                    IDProf.setImageBitmap(thumbnail);
                    BitMapToString(thumbnail);
                }
            }
        }
        public String BitMapToString(Bitmap userImage1) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            userImage1.compress(Bitmap.CompressFormat.PNG, 60, baos);
            byte[] b = baos.toByteArray();
            Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
            return Document_img1;
        }

        public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
            int width = image.getWidth();
            int height = image.getHeight();

            float bitmapRatio = (float)width / (float) height;
            if (bitmapRatio > 1) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }
            return Bitmap.createScaledBitmap(image, width, height, true);
        }

        private void SendDetail() {
            final ProgressDialog loading = new ProgressDialog(Uplode_Reg_Photo.this);
            loading.setMessage("Please Wait...");
            loading.show();
            loading.setCanceledOnTouchOutside(false);
            RetryPolicy mRetryPolicy = new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfiURL.Registration_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                loading.dismiss();
                                Log.d("JSON", response);
                                 public String getPixels = require('get-pixels');
                                public String quantize = require('quantize');

                               int pixels = imgData;
                               pixelArray = [];

                                    for (let i = 0, offset, r, g, b, a; i < pixelCount; i = i + quality) {
                                        offset = i * 4;
                                        r = pixels[offset + 0];
                                        g = pixels[offset + 1];
                                        b = pixels[offset + 2];
                                        a = pixels[offset + 3];

                                        // If pixel is mostly opaque and not white
                                        if (typeof a === 'undefined' || a >= 125) {
                                            if (!(r > 250 && g > 250 && b > 250)) {
                                                pixelArray.push([r, g, b]);
                                            }
                                        }
                                    }
                                    return pixelArray;
                                }

                                function validateOptions(options) {
                                        let { colorCount, quality } = options;

                                if (typeof colorCount === 'undefined' || !Number.isInteger(colorCount)) {
                                    colorCount = 10;
                                } else if (colorCount === 1 ) {
                                    throw new Error('colorCount should be between 2 and 20. To get one color, call getColor() instead of getPalette()');
                                } else {
                                    colorCount = Math.max(colorCount, 2);
                                    colorCount = Math.min(colorCount, 20);
                                }

                                if (typeof quality === 'undefined' || Number.isInteger(quality)) {
                                    quality = 10;
                                } else if (quality < 1) {
                                    quality = 10;
                                }

                                return {
                                        colorCount,
                                        quality
                                }
}

                                function loadImg(img) {
                                return new Promise((resolve, reject) => {
                                    getPixels(img, function(err, data) {
                                        if(err) {
                                            reject(err)
                                        } else {
                                            resolve(data);
                                        }
                                    })
                                });
}

                                function getColor(img, quality) {
                                    return new Promise((resolve, reject) => {
                                        getPalette(img, 5, quality)
                                                .then(palette => {
                                                resolve(palette[0]);


                                function getPalette(img, colorCount = 10, quality = 10) {
    const options = validateOptions({
                                            colorCount,
                                            quality
                                    });

                                    return new Promise((resolve, reject) => {
                                        loadImg(img)
                                                .then(imgData => {
                const pixelCount = imgData.shape[0] * imgData.shape[1];
                const pixelArray = createPixelArray(imgData.data, pixelCount, options.quality);

                const cmap = quantize(pixelArray, options.colorCount);
                const palette = cmap? cmap.palette() : null;

                                        resolve(palette);
            })
            .catch(err => {
                                                reject(err);
            })
                                    });
                                }

                                module.exports = {
                                        getColor,
                                        getPalette
                                };


                                JSONObject eventObject = new JSONObject(response);
                                String error_status = eventObject.getString("error");
                                if (error_status.equals("true")) {
                                    String error_msg = eventObject.getString("msg");
                                    ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                    alertDialogBuilder.setTitle("Vendor Detail");
                                    alertDialogBuilder.setCancelable(false);
                                    alertDialogBuilder.setMessage(error_msg);
                                    alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                        }
                                    });
                                    alertDialogBuilder.show();

                                } else {
                                    String error_msg = eventObject.getString("msg");
                                    ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                    alertDialogBuilder.setTitle("Registration");
                                    alertDialogBuilder.setCancelable(false);
                                    alertDialogBuilder.setMessage(error_msg);
//                                alertDialogBuilder.setIcon(R.drawable.doubletick);
                                    alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent=new Intent(Uplode_Reg_Photo.this,Log_In.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                    alertDialogBuilder.show();
                                }
                            }catch(Exception e){
                                Log.d("Tag", e.getMessage());

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("No connection");
                                alertDialogBuilder.setMessage(" Connection time out error please try again ");
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                alertDialogBuilder.show();
                            } else if (error instanceof AuthFailureError) {
                                ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("Connection Error");
                                alertDialogBuilder.setMessage(" Authentication failure connection error please try again ");
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                alertDialogBuilder.show();
                                //TODO
                            } else if (error instanceof ServerError) {
                                ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("Connection Error");
                                alertDialogBuilder.setMessage("Connection error please try again");
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                alertDialogBuilder.show();
                                //TODO
                            } else if (error instanceof NetworkError) {
                                ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("Connection Error");
                                alertDialogBuilder.setMessage("Network connection error please try again");
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                alertDialogBuilder.show();
                                //TODO
                            } else if (error instanceof ParseError) {
                                ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("Error");
                                alertDialogBuilder.setMessage("Parse error");
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                alertDialogBuilder.show();
                            }
//                        Toast.makeText(Login_Activity.this,error.toString(), Toast.LENGTH_LONG ).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(KEY_User_Document1,Document_img1);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            stringRequest.setRetryPolicy(mRetryPolicy);
            requestQueue.add(stringRequest);
        }


        @Override
        public void onClick(View v) {
            if (Document_img1.equals("") || Document_img1.equals(null)) {
                ContextThemeWrapper ctw = new ContextThemeWrapper( Uplode_Reg_Photo.this, R.style.Theme_AlertDialog);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                alertDialogBuilder.setTitle("Id Prof Can't Empty ");
                alertDialogBuilder.setMessage("Id Prof Can't empty please select any one document");
                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                alertDialogBuilder.show();
                return;
            }
            else{

                if (AppStatus.getInstance(this).isOnline()) {
                    SendDetail();


                    //           Toast.makeText(this,"You are online!!!!",Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this,"You are not online!!!!",Toast.LENGTH_LONG).show();
                    Log.v("Home", "############################You are not online!!!!");
                }
