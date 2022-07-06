import java.util.ArrayList;

public class Quotes {
    private String _id;
    ArrayList< Object > tags = new ArrayList < Object > ();
    private String content;
    private String author;
    private String authorSlug;
    private float length;
    private String dateAdded;
    private String dateModified;


    // Getter Methods

    public String get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public float getLength() {
        return length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public ArrayList<Object> getTags() {
        return tags;
    }

    // Setter Methods

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public void setTags(ArrayList<Object> tags) {
        this.tags = tags;
    }
}

