package se.tidensavtryck;

import java.util.List;

import se.tidensavtryck.model.Place;
import se.tidensavtryck.model.Route;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class RouteActivity extends MapActivity {

    MapView mMapView;
    List<Overlay> mMapOverlays;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route);

        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setBuiltInZoomControls(true);

        mMapOverlays = mMapView.getOverlays();

        Route route = Route.createKnaustRoute();
        addOverlaysFromRoute(route, mMapOverlays);

        final MapController mc = mMapView.getController();

        if (mMapOverlays.size() > 0) {
            PlaceItemizedOverlay first = (PlaceItemizedOverlay) mMapOverlays.get(0);
            GeoPoint point = first.getCenter();

            mc.animateTo(point);
            mc.setZoom(16);
        }
    }

    /**
     * Add overlays from a {@link Route}.
     * @param route The route.
     * @param mapOverlays The overlays.
     */
    private void addOverlaysFromRoute(Route route, List<Overlay> mapOverlays) {
    	int index = 1;
        for (Place place : route.getPlaces()) {
            mapOverlays.add(createPlaceOverlay(place, index));
            index++;
        }
    }

    /**
     * Creates an overlay from a {@link Place}.
     * @param place The place.
     * @return An overlay.
     */
    private PlaceItemizedOverlay createPlaceOverlay(Place place, int index) {
        Drawable drawable = createMarker(index);
        PlaceItemizedOverlay itemizedOverlay = new PlaceItemizedOverlay(drawable, mMapView);

        GeoPoint point = new GeoPoint(
                (int)(place.getGeoLocation().getLatitude()*1E6),
                (int)(place.getGeoLocation().getLongitude()*1E6));
        OverlayItem overlayItem = new OverlayItem(point, "Runsten", 
                place.getDescription());

        itemizedOverlay.addOverlay(overlayItem);

        return itemizedOverlay;
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    
    private Drawable createMarker(int index) {
    	Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker_1);


    	// create a mutable bitmap with the same size as the background image
    	Bitmap bmOverlay = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), 
    	    Bitmap.Config.ARGB_4444);
    	// create a canvas on which to draw
    	Canvas canvas = new Canvas(bmOverlay);

    	Paint paint = new Paint();
    	paint.setColor(Color.BLACK);
    	paint.setTextSize(24);
    	paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

    	// if the background image is defined in main.xml, omit this line
    	canvas.drawBitmap(mBitmap, 0, 0, null);
    	// draw the text and the point
    	canvas.drawPoint(22, 26, paint);
    	canvas.drawText(""+(index+9), 22+3, 26+3, paint);

    	// set the bitmap into the ImageView
    	return new BitmapDrawable(bmOverlay);
    }
}
