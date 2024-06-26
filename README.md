## Pdfium Android bindings

### This is a fork of [barteksc/AndroidPdfViewer](https://github.com/barteksc/AndroidPdfViewer). This is an up to date version of the library, since the original library is abandonware. It uses latest version of Pdfium, it is migrated to AndroidX, it uses latest target SDK level.

### Import

```
repositories {
    maven { url "https://andob.io/repository/open_source" }
}
```

```
dependencies {
    implementation 'ro.andob.pdfium-android:pdfium-android:1.1.1'
    implementation 'ro.andob.pdfium-android:pdfium-android-viewer:1.0.3'
}
```

### Usage

Examples took from the original repository:
 
```
<com.github.barteksc.pdfviewer.PDFView
        android:id="@+id/pdfView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

```
pdfView.fromUri(Uri)
or
pdfView.fromFile(File)
or
pdfView.fromBytes(byte[])
or
pdfView.fromStream(InputStream) // stream is written to bytearray - native code cannot use Java Streams
or
pdfView.fromSource(DocumentSource)
or
pdfView.fromAsset(String)
    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
    .enableSwipe(true) // allows to block changing pages using swipe
    .swipeHorizontal(false)
    .enableDoubletap(true)
    .defaultPage(0)
    // allows to draw something on the current page, usually visible in the middle of the screen
    .onDraw(onDrawListener)
    // allows to draw something on all pages, separately for every page. Called only for visible pages
    .onDrawAll(onDrawListener)
    .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
    .onPageChange(onPageChangeListener)
    .onPageScroll(onPageScrollListener)
    .onError(onErrorListener)
    .onPageError(onPageErrorListener)
    .onRender(onRenderListener) // called after document is rendered for the first time
    // called on single tap, return true if handled, false to toggle scroll handle visibility
    .onTap(onTapListener)
    .onLongPress(onLongPressListener)
    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
    .password(null)
    .scrollHandle(null)
    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
    // spacing between pages in dp. To define spacing color, set view background
    .spacing(0)
    .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
    .linkHandler(DefaultLinkHandler)
    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
    .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
    .pageSnap(false) // snap pages to screen boundaries
    .pageFling(false) // make a fling change only a single page like ViewPager
    .nightMode(false) // toggle night mode
    .load();
```


### Licensed under Apache License
