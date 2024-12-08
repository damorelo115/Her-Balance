package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DashboardController {

    @FXML
    private Pane sidepane;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button periodTrackButton;

    @FXML
    private Button workoutButton;

    @FXML
    private Button mealPlanButton;

    @FXML
    private Button wellnessTipButton;

    @FXML
    private Button affirmationButton;

    @FXML
    private Button quoteButton;

    @FXML
    private Button editProfilePhotoButton;

    @FXML
    private TextArea affirmationTextArea;

    @FXML
    private TextArea wellnessTextArea;

    @FXML
    private TextArea dailyQuoteArea;

    @FXML
    private Label userlabel;

    // ImageViews as buttons
    @FXML
    private ImageView dashboardIcon;

    @FXML
    private ImageView heartIcon;

    @FXML
    private ImageView bloodDropIcon;

    @FXML
    private ImageView signOutIcon;

    @FXML
    private ImageView workoutIcon;

    @FXML
    private ImageView mealPlanIcon;

    @FXML
    private ImageView userIcon;

    @FXML
    private ImageView notificationIcon;

    @FXML
    public void initialize() throws ExecutionException, InterruptedException {

        if (Main.theUser.getUserEmail() != null) {

            fetchFirstName();
        }

    }

    // This method searches Firestore for the user's first name based on the email used to log in
    private void fetchFirstName() {

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = Main.fstore.collection("Users")
                .whereEqualTo("Email", Main.theUser.getUserEmail())
                .get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;

        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {

                    String firstname = document.getString("FirstName");

                    userlabel.setText("Hi, " + firstname + "!");

                }

            } else {

                userlabel.setText("User not found");
            }

        } catch (InterruptedException | ExecutionException e) {

            throw new RuntimeException(e);
        }

    }


    @FXML
    private void handleProfilePhoto() {
        // Open a file chooser to allow the user to select a profile photo
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(userIcon.getScene().getWindow());

        if (file != null) {
            try {
                // Load the image
                Image originalImage = new Image(file.toURI().toString());

                // Resize the image to fit 100x100
                ImageView imageView = new ImageView(originalImage);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);

                // Convert the file to a Blob and save it directly in Firestore
                savePhotoToFirestore(String.valueOf(file));

                // Update the userIcon with the selected photo
                Image profileImage = new Image(file.toURI().toString(), 100, 100, true, true);
                userIcon.setImage(profileImage);

                // Show success alert
                showAlert("Success", "Profile photo uploaded and saved successfully!");

            } catch (Exception e) {
                // Show error alert
                showAlert("Error", "An error occurred while uploading the profile photo: " + e.getMessage());
            }
        }
    }

    @FXML
    public void handleEditProfilePhoto(ActionEvent actionEvent) {
        // Reuse the handleProfilePhoto method for editing profile photo
        handleProfilePhoto();
    }

    private String uploadToFirebaseStorage(File file) throws IOException {
        // Get the default storage instance
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // Define the bucket and the file path within the bucket
        String bucketName = "your-firebase-storage-bucket-name.appspot.com";
        String fileName = "profile_photos/" + Main.theUser.getUserEmail() + ".jpg";

        // Read the file content into a byte array
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Upload the file to Firebase Storage
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType("image/jpeg")
                .build();

        try { storage.create(blobInfo, fileContent); // Use byte array instead of FileInputStream
        System.out.println("File uploaded to Firebase Storage successfully!");
            System.out.println("File uploaded to Firebase Storage successfully!");
            return "https://storage.googleapis.com/" + bucketName + "/" + fileName; // Construct public URL
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void savePhotoToFirestore(String photoUrl) {
        try {
            // Create a photo document with the photo URL and timestamp
            Map<String, Object> photoData = new HashMap<>();
            photoData.put("url", photoUrl);
            photoData.put("timestamp", System.currentTimeMillis());

            // Save the photo to the user's "photos" collection in Firestore
            Main.fstore.collection("Users")
                    .document(Main.theUser.getUserEmail())
                    .collection("photos")
                    .add(photoData)
                    .get(); // Wait for the Firestore operation to complete

            System.out.println("Photo URL saved to Firestore successfully!");
        } catch (Exception e) {
            System.err.println("Error saving photo to Firestore: " + e.getMessage());
            showAlert("Error", "Failed to save the photo to Firestore.");
        }
    }





    // This event is called to log the user out of the application and returns the user to the login screen
    @FXML
    private void logout(ActionEvent event) throws IOException {

        Stage stage;

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            stage = (Stage) logoutButton.getScene().getWindow();

            System.out.println("User logged out!");

            stage.close();

            UserLogin.loadUserLoginScene(stage);
        }


    }

    // Display alert dialogs
    private void showAlert(String error, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Click");
        alert.setHeaderText(null);
        alert.setContentText(message + " icon clicked!");
        alert.showAndWait();
    }

    @FXML
    protected void dashboardButtonClick() {

        try {

            Stage stage = (Stage) dashboardButton.getScene().getWindow();

            Dashboard.loadDashboardScene(stage);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void periodButtonClick() throws IOException {

        try {
            Stage stage = (Stage) periodTrackButton.getScene().getWindow();

            PeriodTracker.loadPeriodTrackerScene(stage);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

        @FXML
        protected void workoutButtonClick () {

            try {

                Stage stage = (Stage) workoutButton.getScene().getWindow();

                Fitness.loadFitnessTrackerScene(stage);
            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        }

        @FXML
        private void mealPlanButtonClick() throws IOException {

            try {

                Stage stage = (Stage) mealPlanButton.getScene().getWindow();

                MealPlanner.loadMealPlannerScene(stage);
            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        }



        // Method that uses an API to fetch a daily affirmation that the user can read
        @FXML
        private void fetchAffirmation (ActionEvent event) throws IOException, InterruptedException {

            affirmationButton.setDisable(true);

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://positivity-tips.p.rapidapi.com/api/positivity/affirmation"))
                        .header("x-rapidapi-key", "4ab2742280msh3a57872a2958f31p11c716jsnbfd1d0396e65")
                        .header("x-rapidapi-host", "positivity-tips.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());

                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

                String affirmationResponse = jsonObject.get("affirmation").getAsString();

                affirmationTextArea.setText(affirmationResponse + "\n");
                affirmationTextArea.setEditable(false);
                affirmationTextArea.setFont(new Font("Poppins", 18));
                affirmationTextArea.setWrapText(true);
            } catch (Exception e) {

                throw new RuntimeException(e);
            } finally {

                affirmationButton.setDisable(false);

            }

        }


        // Method that uses an API to fetch a wellness tip that the user can read
        @FXML
        private void fetchWellnessTip (ActionEvent event) throws IOException, InterruptedException {

            wellnessTipButton.setDisable(true);

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://positivity-tips.p.rapidapi.com/api/positivity/wellness"))
                        .header("x-rapidapi-key", "4ab2742280msh3a57872a2958f31p11c716jsnbfd1d0396e65")
                        .header("x-rapidapi-host", "positivity-tips.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.body());

                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

                String wellnessResponse = jsonObject.get("tip").getAsString();

                wellnessTextArea.setText(wellnessResponse + "\n");
                wellnessTextArea.setEditable(false);
                wellnessTextArea.setFont(new Font("Poppins", 18));
            }

            catch (Exception e) {

                throw new RuntimeException(e);
            }
            finally {

                wellnessTipButton.setDisable(false);
            }

        }

    // Method that uses an API to fetch a positive quote that the user can read
    @FXML
    private void fetchpositiveQuote (ActionEvent event) throws IOException, InterruptedException {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://positivity-tips.p.rapidapi.com/api/positivity/quote"))
                    .header("x-rapidapi-key", "607d095e27msh7f1200d0b409a4ap1d4380jsnddd3430a3d0c")
                    .header("x-rapidapi-host", "positivity-tips.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            String quoteAuthor = jsonObject.get("author").getAsString();
            String quoteResponse = jsonObject.get("quote").getAsString();

            dailyQuoteArea.setText(quoteAuthor + "\n" + quoteResponse + "\n");
            dailyQuoteArea.setWrapText(true);
            dailyQuoteArea.setEditable(false);
            dailyQuoteArea.setFont(new Font("Poppins", 18));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        finally {
            quoteButton.setDisable(false);
        }

    }


}
