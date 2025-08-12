# PixelNova: An Asynchronous Image Processor

Have you ever wondered how image filters on popular apps like Instagram or Snapchat work? While they might seem complex, the basic principles of image filtering are quite accessible. This project, **PixelNova**, is a simple image filtering application built with Java and JavaFX to demonstrate these concepts.

By the end of this project, you will have a better understanding of **image processing**, how to build a **GUI with JavaFX**, and how to use core Java concepts to create a simple, yet powerful, image processing application.

---

### 1. What is PixelNova?

PixelNova is a simple, multi-threaded image processing application built with Java and the JavaFX framework. It is a desktop tool that allows a user to apply various filters to a digital image. The application is designed to be **easily extensible**, allowing new filters to be added with minimal effort.

Functionally, the application can:
* **Apply a variety of image filters**, such as:
    * **Grayscale**: Converts the image to black and white.
    * **Sepia Tone**: Gives the image a warm, brownish tint.
    * **Color Tint**: Applies a color overlay to the image.
    * **Invert**: Inverts the colors of the image.
    * **Posterize**: Reduces the number of colors in the image.
    * **Solarize**: Inverts the colors of pixels above a certain brightness threshold.
* **Display the filtered images on a canvas**.

---

### 2. LLD (Low-Level Design)


(<img width="1349" height="797" alt="Screenshot 2025-08-12 041712" src="https://github.com/user-attachments/assets/442816ea-fe8a-45f5-b36a-193d31cdc3af" />
)

---

### 3. Prerequisites

To build and run PixelNova, you'll need the following tools:
* **Java Development Kit (JDK) 21 or higher**
* **Maven** (for dependency management)
* An IDE like **IntelliJ IDEA**

---

### 4. Project Structure
(<img width="534" height="297" alt="Screenshot 2025-08-12 040727" src="https://github.com/user-attachments/assets/71d6f0e4-2f41-4007-8b48-c2683a9df6ea" />
)

The project is organized into the following main packages for clarity and modularity:

- `filter`: **Image filter implementations** - This directory contains the specific code for each image filter, such as grayscale, sepia, invert, and posterize. Each class in this directory defines the logic for how to transform an image's pixels according to a particular effect.

- `image`: **Image display components** - This is where the classes responsible for rendering the image on the screen are located. This could include the JavaFX `Canvas` component and any related logic for drawing pixels and handling image dimensions.

- `io`: **Image reading/saving** - This directory handles the input and output operations. It contains classes for loading an image from a file into memory and for saving the processed image back to a file.

- `processor`: **Core image processing logic** - This package is the heart of the application. It contains the high-level logic that orchestrates the filtering process. This includes managing the thread pool (e.g., `ExecutorService`), dividing the image into sections for parallel processing, and coordinating the application of filters.

- `HelloApplication.java`: **Main application class** - This is the entry point of the entire application. It's the primary class that sets up the JavaFX application window, initializes the GUI components (like the canvas and buttons), and connects the user interface to the backend processing logic. It handles user interactions and launches the filtering tasks.

---

### 5. Working of the Application (Core Concept)

This is how the application works at its core, combining parallel processing with safe GUI updates.

#### How the Processing Works
1.  **The Image is a Grid of Pixels**: Think of your image as a grid of tiny dots, called pixels. A filter's job is to visit each pixel and change its color based on a mathematical rule (e.g., the grayscale rule).
2.  **Divide the Grid**: Instead of processing the entire grid with one worker (a single thread), we divide the grid into horizontal strips.
3.  **Process in Parallel**: An `ExecutorService` acts as the manager, sending each "worker thread" to its assigned section. All workers start processing their rows at the same time. They don't need to know about each other, because their sections are independent.
4.  **Combine the Results**: Once every worker is finished, the entire image has been filtered. Because they all worked at once, the total time taken is much less than the sequential approach.

#### How Pixel Printing on the Canvas Works
This part has a very important rule that you cannot break: the **"One Boss" rule** for the GUI.

Think of your application's user interface (the canvas, buttons, etc.) as a delicate masterpiece being assembled. To prevent chaos, there is only **one boss** who is allowed to touch or change it. In JavaFX, this boss is called the **JavaFX Application Thread**.

* **Workers Do the Heavy Lifting**: The worker threads from our `ExecutorService` do the hard work of filtering the image in the background. They are not the boss and are **not allowed to touch the UI**.
* **Reporting to the Boss**: Once a worker thread has finished filtering its image (or its section of an image), it can't just draw it on the screen. Instead, it puts the finished image in a queue and sends a message to the boss, saying, "Hey, I'm done. Please draw this image for me when you have a moment."
* **The Boss Draws the Image**: The JavaFX Application Thread (the boss) picks up this request from the queue. Since it's the only one with permission, it safely takes the filtered image and draws the pixels onto the canvas for the user to see.

---

### 6. Running the Application

To run the application, navigate to the project's root directory in your terminal and execute the following command:

```bash
mvn clean javafx:run
