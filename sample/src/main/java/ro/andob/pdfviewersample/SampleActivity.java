package ro.andob.pdfviewersample;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.net.URL;
import ro.andob.rapidroid.Run;

public class SampleActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        PDFView pdfView = new PDFView(this);
        pdfView.setBackgroundColor(Color.LTGRAY);
        setContentView(pdfView);

        String url = "https://pdfobject.com/pdf/sample.pdf";
        File file = getFileStreamPath("sample.pdf");

        Run.async(() -> FileUtils.copyURLToFile(new URL(url), file)).onSuccess(() ->
            pdfView.fromFile(file)
                .autoSpacing(false).spacing(4)
                .enableSwipe(true)
                .enableDoubletap(true)
                .load());
    }
}
