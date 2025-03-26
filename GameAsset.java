// Subject Interface
public interface GameAsset {
    void load();
    void display();
}

// RealSubject
public class HighResTexture implements GameAsset {
    private String filename;

    public HighResTexture(String filename) {
        this.filename = filename;
        load(); // load texture from disk or network
    }

    @Override
    public void load() {
        System.out.println("Loading high-resolution texture from " + filename);
        // Simulate a heavy load operation
    }

    @Override
    public void display() {
        System.out.println("Displaying texture: " + filename);
    }
}

// Proxy
public class TextureProxy implements GameAsset {
    private String filename;
    private HighResTexture realTexture; // real subject

    public TextureProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void load() {
        // Defer loading until necessary
        if (realTexture == null) {
            realTexture = new HighResTexture(filename);
        }
    }

    @Override
    public void display() {
        // Ensure the real texture is loaded before use
        if (realTexture == null) {
            load();
        }
        realTexture.display();
    }
}

// Client
public class Game {
    public static void main(String[] args) {
        GameAsset texture = new TextureProxy("boss_texture.png");
        // Proxy is created, but actual texture not loaded yet

        // At some point, we decide to use the texture:
        texture.display(); 
        // Actual loading happens here, and then texture is displayed
    }
}
