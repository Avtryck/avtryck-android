package se.tidensavtryck.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable{
	private String title;
	private String description;
	private User createdBy;
    private List<Place> places;
    private int likes = 0;
	private int durationInMinutes;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

	public Route() {
	}

	public Route(Parcel parcel) {
		title = parcel.readString();
        createdBy = parcel.readParcelable(User.class.getClassLoader());
		description = parcel.readString();
		durationInMinutes = parcel.readInt();
		likes = parcel.readInt();

		places = new ArrayList<Place>();
		parcel.readTypedList(places, Place.CREATOR);
	}

	public List<Place> getPlaces() {
		return Collections.unmodifiableList(places);
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public User getCreatedBy() {
		return createdBy;
	}
	
	public int getDurationInMinutes() {
		return durationInMinutes;
	}
	
	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
        dest.writeParcelable(createdBy, 0);
		dest.writeString(description);
		dest.writeInt(durationInMinutes);
		dest.writeInt(likes);
		
		dest.writeTypedList(places);
	}

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        public Route createFromParcel(Parcel parcel) {
            return new Route(parcel);
        }

        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    public String toString() {
    	return title;
    }
    
}
