# 🏋️ IRON ASCENT 2.2.0

> **Your personalized offline fitness companion.**
> تطبيق لياقة بدنية عربي/إنجليزي يعمل بالكامل بدون إنترنت، ويكيّف برنامج التمرين والتغذية حسب **الجنس، الهدف، العمر، الطول والوزن**.

**Developed by Abdelrhman Hamed**
**Copyright © 2026 IRON ASCENT**

---

## 🚀 Overview

**IRON ASCENT** is an Android fitness companion designed to give every user a personalized training and nutrition experience — completely **offline**.

The app analyzes your profile and automatically generates:

* 🏋️ Personalized workout split
* 🔥 Daily calorie target
* 🥩 Daily protein target
* 💧 Daily water target
* ⚖️ BMI & category
* 📈 Weight progress tracking
* 🏆 Goal-aware achievement badges
* 🤖 Offline AI fitness assistant
* 🍽️ Personalized nutrition guidance
* 🖼️ Exercise images for every movement
* 🔔 Meal reminders

> **No accounts. No API keys. No cloud dependency. No internet required at runtime.**

---

# ✨ What's New in 2.2.0

## 🚻 Gender-Aware Training Programs

The onboarding experience now asks for:

* **Gender:** ذكر / أنثى
* **Goal:** خسارة وزن / زيادة وزن / تناسق عام

Both are required before completing the profile.

### 👨 Male Program

A **5-day training split**:

| Day           | Workout          |
| ------------- | ---------------- |
| 🗓️ Day 1     | Back + Traps     |
| 🗓️ Day 2     | Chest + Arms     |
| 🗓️ Day 3     | Legs + Shoulders |
| 🗓️ Day 4     | Upper Body       |
| 🗓️ Day 5     | Legs             |
| 😴 Other Days | Rest             |

### 👩 Female Program

A **3-day training split**:

| Day           | Workout                   |
| ------------- | ------------------------- |
| 🗓️ Day 1     | Lower Body + Glutes       |
| 🗓️ Day 2     | Upper Body                |
| 🗓️ Day 3     | Lower Body + Glutes Focus |
| 😴 Other Days | Rest                      |

Changing gender from the profile instantly updates:

* Workout program
* Day tabs
* Streak calendar
* Badge system
* Exercise selection

**No reinstall required.**

---

# 🖼️ Exercise Images

Every exercise includes a visual demonstration.

The app uses a **two-level offline image system**:

### 1️⃣ Real Exercise Photos

Real photos are stored at:

```text
photos/male/<key>.jpg
photos/female/<key>.jpg
```

The CI build downloads verified and properly licensed photos from **Wikimedia Commons**.

### 2️⃣ Offline Illustration Fallback

If a real photo is unavailable, the app automatically uses:

```text
images/male/<key>.svg
images/female/<key>.svg
```

These are original vector illustrations bundled directly with the application.

> **Result:** No broken exercise images — even when completely offline.

### 🏋️ New Female Exercises

The female program includes dedicated images for movements such as:

* Romanian Deadlift
* Hip Abduction
* Bulgarian Split Squat
* DB Shoulder Press

### ➕ Add Your Own Photos

Simply place a correctly licensed image inside:

```text
app/src/main/assets/www/photos/<male|female>/<key>.jpg
```

No code changes are required.

See:

```text
app/src/main/assets/www/photos/README.md
app/src/main/assets/www/PHOTO_CREDITS.md
```

---

# 🧠 Personalized Recommendation Engine

IRON ASCENT calculates your nutritional requirements using the **Mifflin-St Jeor equation**.

The calculation considers:

* Gender
* Age
* Height
* Weight
* Training activity
* Goal

### 📊 The app calculates

| Metric      | Description               |
| ----------- | ------------------------- |
| 🔥 Calories | Daily calorie target      |
| 🥩 Protein  | Daily protein requirement |
| 💧 Water    | Daily water target        |
| ⚖️ BMI      | BMI + category            |

Your goal then adjusts the calorie recommendation:

* 📉 **Weight Loss** → Calorie deficit
* 📈 **Weight Gain** → Calorie surplus
* ⚖️ **Recomposition** → Slight deficit

The nutrition screen also provides goal-specific suggestions, such as reducing rice portions during a deficit or adding healthy fats during a surplus.

---

# 🤖 IRON AI

IRON AI has been completely rebuilt.

It is **not a browser shortcut to ChatGPT**.

Instead, it is a genuine **offline, rule-based fitness assistant** that reads your locally stored profile and calculated targets.

### ⚡ Quick Questions

You can instantly ask:

* 🍽️ What should I eat after training?
* 💧 How much water do I need?
* 🥩 How much protein do I need?
* 📅 What's today's workout?
* 😴 I'm exhausted today — what should I do?
* 🔄 What can replace an exercise?
* 📉 Why has my weight stopped changing?
* 🎯 Show me my complete plan

You can also type your own question using the free-text input.

### 🔐 Privacy

IRON AI:

* ❌ Does not use an external AI API
* ❌ Does not require an API key
* ❌ Does not require internet
* ❌ Does not upload your profile
* ✅ Runs locally on the device

> ⚠️ **Disclaimer:** IRON AI is a built-in fitness assistant and is not a replacement for a doctor or certified coach, especially for medical conditions or injuries.

---

# 🏆 Goal-Aware Badges

The badge system now understands your actual goal.

### 📈 Weight Gain / Recomposition

Earn a new badge every:

**+4 kg**

above your starting weight.

### 📉 Weight Loss

Earn a new badge every:

**−4 kg**

below your starting weight.

The system tracks:

* Highest weight for gain/recomposition
* Lowest weight for weight loss
* Starting weight
* Progress toward your goal

---

# 🔥 Workout Experience

IRON ASCENT includes several features designed to make completing workouts more engaging.

### 📊 Live Workout Progress

Every workout displays:

```text
████████░░ 8 / 10 exercises
```

### 🔥 Daily Streak

The app tracks consecutive completed workout days.

**Rest days are automatically counted**, so your streak doesn't break simply because today is a scheduled rest day.

### 🎉 Workout Completion

Complete **100%** of a workout and receive a confetti celebration.

### ⏱️ Rest Timer

Every exercise includes a built-in:

**90-second rest timer**

with an audio completion cue.

### 🖼️ Full-Screen Exercise Images

Tap an exercise image to open it in a full-screen lightbox.

### 💤 Rest Days

Rest days are clearly displayed directly inside the workout day selector.

---

# 🍽️ Nutrition

The nutrition screen provides:

* 🔥 Daily calories
* 🥩 Daily protein
* 💧 Daily water
* 🍱 Pre-built meal plan
* ⏰ Meal reminders
* 💡 Goal-specific nutrition tips

There are **9 pre-built meal slots** with editable reminder times.

You can also enable or disable all meal reminders with one tap.

---

# ⚖️ Weight Tracking

Track your progress directly from the app.

Each entry can contain:

* Weight
* Date
* Personal note

The app automatically calculates:

* Current weight
* Starting weight
* Highest weight
* Lowest weight
* Goal progress
* Achievement badges

---

# 👤 Profile

Your profile contains:

* Name
* Gender
* Goal
* Age
* Height
* Current weight
* Training split
* Calories
* Protein
* Water
* BMI
* Achievement badges

Everything is stored locally on your device.

---

# 📱 App Screens

| Screen           | Features                                                                      |
| ---------------- | ----------------------------------------------------------------------------- |
| 🏋️ **البرنامج** | Personalized split, exercises, sets/reps, checkboxes, timer, progress, streak |
| 🍽️ **الأكل**    | Calories, protein, water, meals, reminders                                    |
| ⚖️ **الوزن**     | Weight history, progress, badges                                              |
| 🤖 **AI**        | Offline fitness assistant                                                     |
| 👤 **حسابي**     | Profile, goals, BMI, nutrition targets, badges                                |

---

# 📴 100% Offline

IRON ASCENT is designed to work without an internet connection.

### Available Offline

* 🏋️ Workouts
* 🖼️ Exercise images
* 👤 Profile
* ⚖️ Weight history
* 🏆 Badges
* 🍽️ Nutrition targets
* 🤖 IRON AI
* ⏱️ Rest timer
* 🔥 Streak tracking

Real exercise photos are bundled into the APK during the build process.

> **The app does not download exercise images at runtime.**

---

# 🖼️ Image Licensing

### Original Illustrations

The SVG illustrations are original, hand-built vector diagrams created specifically for IRON ASCENT.

### Real Photos

Real exercise photos are downloaded during CI builds from **Wikimedia Commons**.

Attribution and license information are available in:

```text
app/src/main/assets/www/PHOTO_CREDITS.md
```

If you add your own photos, make sure you have the legal right to redistribute them inside the application.

---

# 🛠️ Build with GitHub Actions

The repository includes:

```text
.github/workflows/build-apk.yml
```

Every push to `main` — or manual workflow execution — performs the following:

```text
┌──────────────────────────┐
│      GitHub Actions      │
└────────────┬─────────────┘
             ↓
      Java 17 + Android SDK
             ↓
     Download exercise photos
             ↓
       Verify image files
             ↓
       Copy gender folders
             ↓
         Build APK
             ↓
   Upload APK as an artifact
```

### 📦 Build Artifact

```text
IRON-ASCENT-2.2.0-APK
```

Generated APK:

```text
IRON-ASCENT-2.2.0.apk
```

---

# 📥 Download

### Latest APK Releases

[Download IRON ASCENT APKs](https://drive.google.com/drive/folders/1crLIsu3hn63VU13fN3Q-QbLTXSrVxIzq?usp=sharing&utm_source=chatgpt.com)

---

# 🔐 Permissions

IRON ASCENT requires only the permissions needed for its core functionality:

| Permission               | Purpose                                      |
| ------------------------ | -------------------------------------------- |
| `POST_NOTIFICATIONS`     | Meal reminder notifications                  |
| `RECEIVE_BOOT_COMPLETED` | Reschedule reminders after reboot/app update |

---

# 🧩 Version History

## 2.2.0

* 🚻 Gender-aware onboarding
* 🎯 Goal-aware personalization
* 👨 5-day male training split
* 👩 3-day female training split
* 🖼️ Gender-matched exercise images
* 📸 Wikimedia photo integration
* 🧠 Mifflin-St Jeor calorie calculation
* 🥩 Personalized protein targets
* 💧 Personalized water targets
* ⚖️ BMI calculation
* 🤖 Offline IRON AI
* 🏆 Goal-aware badges
* 🍽️ Goal-adaptive nutrition recommendations
* 🔄 Exercise substitutions
* 📉 Weight plateau guidance

## 2.1.1

* 🖼️ Fixed biceps curl and wrist curl photos
* 🏋️ Fixed hip-thrust image
* 🛡️ Image verification in CI
* 📊 Live workout progress
* 🔥 Daily streak system
* 🎉 Workout completion confetti
* ⏱️ 90-second rest timer
* 🔍 Full-screen exercise images
* 💤 Rest-day indicators

## 2.1.0

* 👤 First-run profile
* 👋 Personalized welcome
* ⚖️ Weight history
* 🏆 Weight badges
* 🏋️ English + Arabic workout names
* 🖼️ Offline exercise images
* 🍽️ Nutrition screen
* 🔔 Meal reminders
* 👨‍💻 Developer attribution

---

# 👨‍💻 Developer

## Abdelrhman Hamed

**IRON ASCENT**
*Train smarter. Track everything. Ascend.*

---

<p align="center">

### 🏋️ IRON ASCENT 2.2.0

**Your body. Your goal. Your program.**

**100% Offline • Personalized • Private**

</p>
