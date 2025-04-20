/**
 * Copyright 2016 Bartosz Schiller
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.barteksc.pdfviewer;

import androidx.lifecycle.LifecycleOwner;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import com.shockwave.pdfium.util.Size;
import ro.andob.rapidroid.Run;
import ro.andob.rapidroid.future.Future;

class DecodingTask {

    private final PdfiumCore pdfiumCore;
    private final String password;
    private final DocumentSource docSource;
    private final int[] userPages;

    DecodingTask(DocumentSource docSource, String password, int[] userPages, PdfiumCore pdfiumCore) {
        this.docSource = docSource;
        this.userPages = userPages;
        this.password = password;
        this.pdfiumCore = pdfiumCore;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public void decodeIntoView(PDFView pdfView) {

        Future<PdfFile> future = Run.future(() -> {
            PdfDocument pdfDocument = docSource.createDocument(pdfView.getContext(), pdfiumCore, password);
            Size pdfViewSize = new Size(pdfView.getWidth(), pdfView.getHeight());
            PdfFile pdfFile = new PdfFile(pdfiumCore, pdfDocument, pdfView.getPageFitPolicy(), pdfViewSize,
                userPages, pdfView.isSwipeVertical(), pdfView.getSpacingPx(), pdfView.isAutoSpacingEnabled(),
                pdfView.isFitEachPage());
            return pdfFile;
        })
        .onError(pdfView::loadError)
        .onSuccess(pdfView::loadComplete);

        if (pdfView.getContext() instanceof LifecycleOwner) {
            future.withLifecycleOwner((LifecycleOwner)pdfView.getContext());
        }
    }
}
