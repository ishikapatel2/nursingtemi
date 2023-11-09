/**
 * Author: Nathan Teku
 * Purpose: To display the emergency plan pdf
 */

package com.example.nursingtemi;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class PDFView extends AppCompatActivity {

    private LinearLayout pdfContainer;
    private PdfRenderer pdfRenderer;
    private ImageView[] pageViews;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

      // TO DO: Back button not working at the moment: Will fix later
        ImageView backButton = findViewById(R.id.backButton);



        backButton.setOnClickListener((v) ->
        {
            Intent obj = new Intent(this, SafetyProcedures.class);
            startActivity(obj);
        });

        pdfContainer = findViewById(R.id.pdfContainer);

        try {
            openPDF();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something Wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void openPDF() throws IOException {
        AssetManager assetManager = getAssets();

        // Specify the name of your PDF file in assets
        String pdfFileName = "plan.pdf";

        // Create a file in internal storage to copy the PDF to
        File file = new File(getFilesDir(), pdfFileName);

        // Check if the file already exists in internal storage, if not, copy it
        if (!file.exists()) {
            InputStream inputStream = assetManager.open(pdfFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        }

        // Open the PDF using ParcelFileDescriptor
        ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);

        // Create a PdfRenderer for the PDF file
        pdfRenderer = new PdfRenderer(parcelFileDescriptor);

        int pageCount = pdfRenderer.getPageCount();

        // Get the LinearLayout container
        LinearLayout pdfContainer = findViewById(R.id.pdfContainer);

        for (int i = 0; i < pageCount; i++) {
            PdfRenderer.Page page = pdfRenderer.openPage(i);

            // Determine the desired zoom level (e.g., 2.0 for doubling the size)
            float zoomLevel = 3.0f;

            // Calculate the new width and height based on the zoom level
            int newWidth = (int) (page.getWidth() * zoomLevel);
            int newHeight = (int) (page.getHeight() * zoomLevel);

            Bitmap bitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);

            // Add each page's ImageView to the LinearLayout
            pdfContainer.addView(imageView);
            page.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the PdfRenderer when the activity is destroyed
        if (pdfRenderer != null) {
            pdfRenderer.close();
        }
    }
}


