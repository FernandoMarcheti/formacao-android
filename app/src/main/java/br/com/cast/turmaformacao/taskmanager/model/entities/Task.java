package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

public class Task implements Parcelable {

    @JsonProperty("_id")
    private Long webId;

    @JsonIgnore
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonIgnore
    private Label label;

    public Long getId() {
        return id;
    }

    public Task() {
        super();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (webId != null ? !webId.equals(task.webId) : task.webId != null) return false;
        return  (id != null ? !id.equals(task.id) : task.id != null);
    }

    @Override
    public int hashCode() {
        int result = webId != null ? webId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "webId=" + webId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", label=" + label +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.webId);
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.label, 0);
    }

    protected Task(Parcel in) {
        this.webId = (Long) in.readValue(Long.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.label = in.readParcelable(Label.class.getClassLoader());
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
