# AssistedInjectSample

This project is a sample for a Guild within Decathlon CSP Team.
The application uses Jetpack Compose for UI and Dagger Hilt for dependency injection.

## Getting Started

### Prerequisites

- Android Studio Ladybug | 2024.2.1
- Kotlin 2.0.10
- Java 17
- Gradle 7.0+

### Installation

1. Clone the repository:
    ```sh
    git clone git@github.com:Devosquad/AssistedInjectSample.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

## Usage

- **List Screen**: Displays a list of emojis. Click on an emoji to navigate to the detail screen.
- **Detail Screen**: Displays the selected emoji with a shared transition effect.

## Changelog

- **1️⃣ Set up AssistedInject**: Configured AssistedInject for dependency injection.
- **2️⃣ Set up Navigation compose**: Integrated navigation component for managing app navigation.
- **3️⃣ Pass complex data with NavBackEntry**: Enabled passing complex data using `NavBackEntry`.
- **4️⃣ Pass complex data with SaveStateHandle**: Enabled passing complex data using
      `SaveStateHandle`.
- **5️⃣ Setup Details / List for Shared Elements**: Configured details and list screens for shared
  elements.
- **6️⃣ Add shared element transition**: Implemented shared element transitions between screens.
- **7️⃣ New FlowLayout**: Added a new layout for arranging items.
