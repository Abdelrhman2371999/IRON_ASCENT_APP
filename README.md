# 🏋️ IRON ASCENT 2.2.0

A bilingual Arabic/English Android fitness companion that adapts to **who you
are**: it asks your gender, goal, age, height, and weight, then generates a
personalized workout split, daily calorie/protein/water targets, and an
offline AI assistant that actually knows your numbers — all stored locally
on your device.

Developed by **Abdelrhman Hamed**.

---

## ✨ What's new in 2.2.0

### 🚻 Gender-aware onboarding & training split
- First-run setup now explicitly asks **Gender** (ذكر / أنثى) and **Goal**
  (خسارة وزن / زيادة وزن / تناسق عام) — both required before saving.
- **Male profile → 5-day split** (Back+Traps / Chest+Arms / Legs+Shoulders /
  Upper Body / Legs), unchanged from the original bro-split design.
- **Female profile → 3-day split** (Lower Body+Glutes / Upper Body /
  Lower+Glutes focus), with rest days shown on all other days of the week.
- Switching gender in your profile instantly swaps the entire program, the
  day tabs, the streak calendar, and the badge system — no reinstall needed.

### 🖼️ Exercise images for every movement, matched to gender
- Every exercise (including the new female-program movements — Romanian
  Deadlift, Hip Abduction, Bulgarian Split Squat, DB Shoulder Press) now has
  a picture, so nobody has to guess what a movement looks like.
- Two-tier image system, fully offline-safe:
  1. **Real photo** (if present) — `photos/male/<key>.jpg` or
     `photos/female/<key>.jpg`. The CI build step downloads verified,
     properly licensed photos from Wikimedia Commons (see
     `app/src/main/assets/www/PHOTO_CREDITS.md`) and copies them into both
     gender folders automatically.
  2. **Illustration fallback** — `images/male/<key>.svg` /
     `images/female/<key>.svg`, a bundled, offline, gender-styled vector
     diagram for every exercise key. If a real photo is ever missing or
     fails to load, the app falls back to this automatically — no broken
     images, ever.
- Want to add your own real photos? Just drop a correctly licensed image at
  `app/src/main/assets/www/photos/<male|female>/<key>.jpg` — see
  `app/src/main/assets/www/photos/README.md` for the full list of exercise
  keys. No code changes required.

### 🧠 Personalized recommendation engine
- Uses the Mifflin-St Jeor formula (gender + weight + height + age) to
  estimate BMR, then applies an activity multiplier based on your training
  split to get your TDEE.
- From that, IRON ASCENT computes and displays, right on your profile and
  nutrition screens:
  - 🔥 **Daily calorie target** — adjusted for your goal (deficit for
    weight loss, surplus for weight gain, slight deficit for recomposition)
  - 🥩 **Daily protein target** (g), scaled to your current bodyweight and
    goal
  - 💧 **Daily water target** (liters)
  - ⚖️ **BMI** and category
- The nutrition page adds a short goal-adaptive note (e.g. reduce rice
  slightly for a deficit, add a spoon of olive oil / extra nuts for a
  surplus) next to the existing meal-by-meal plan and reminders.

### 🤖 IRON AI — now full of real, offline features
- Rebuilt from a "opens ChatGPT in your browser" placeholder into a genuine
  **offline, rule-based assistant** that reads your saved profile and
  computed targets and answers directly — zero internet required, zero API
  keys embedded in the app.
- Quick-question chips for the most common asks:
  - 🍽️ Post-workout meal
  - 💧 Daily water need
  - 🥩 Daily protein need
  - 📅 Today's workout (pulled live from your actual split)
  - 😴 "I'm exhausted today, what do I do?"
  - 🔄 Exercise substitutions (e.g. "what can replace Hip Thrust?")
  - 📉 Weight-plateau troubleshooting, tailored to your goal
  - 🎯 A full summary of your current plan
- Free-text question box still works alongside the chips for anything not
  covered by a preset.
- Clear in-app disclaimer: IRON AI is a built-in assistant, not a
  replacement for a doctor or certified coach for medical situations.

### 🏆 Goal-aware badges
- **Weight-gain / recomposition goal**: a badge every +4 kg above your
  starting weight (tracks your highest weight reached).
- **Weight-loss goal**: a badge every −4 kg below your starting weight
  (tracks your lowest weight reached), so the badge system rewards the
  right direction for your actual goal instead of always rewarding weight
  gain.

---

## What was new in 2.1.1

- 🖼️ Fixed two broken exercise photos (biceps curl, wrist curls) caused by a
  file-extension mismatch between the downloader and the app.
- 🏋️ Fixed the hip-thrust exercise, which was previously showing a leg-press
  machine photo; it now shows a real photo of the same hip-extension
  movement, honestly labeled as a substitute (see `PHOTO_CREDITS.md`).
- 🛡️ The photo download step in CI verifies every file it fetches is a real
  image and retries with a backup source instead of silently shipping a
  blank exercise photo.
- 📊 Live progress bar per workout day (X/Y exercises checked).
- 🔥 Real daily streak counter — counts consecutive days you fully completed
  your workout (rest days count automatically).
- 🎉 Confetti celebration when you finish 100% of a workout day.
- ⏱️ Built-in 90-second rest timer on every exercise, with a sound cue.
- 🔍 Tap any exercise photo to open it full-screen (lightbox).
- 💤 Rest days are marked directly on the day-selector tabs.

## What was new in 2.1.0

- 👤 First-run profile: name, age, height, and starting weight.
- 👋 Personalized welcome using the user's name.
- ⚖️ Local weight history with current/highest weight tracking.
- 🏆 A new badge every +4 kg above the starting weight.
- 🏋️ Workout names in English + Arabic.
- 🖼️ Local exercise images so the core workout experience works offline.
- 🍽️ Dedicated nutrition page with meal reminders.
- 🔔 Android notifications for meal reminders, including after reboot/app
  replacement.
- 👨‍💻 Developer credit: Abdelrhman Hamed.

---

## 📱 Core features

| Screen | What it does |
|---|---|
| **البرنامج (Workout)** | Gender-based split (5-day / 3-day), per-exercise sets/reps, checkboxes, rest timer, per-day progress bar, streak tracking, confetti on full completion |
| **الأكل (Nutrition)** | Computed daily calorie/protein/water targets, 9 pre-built meal slots with editable reminder times, one-tap enable/disable all |
| **الوزن (Weight)** | Log weight with date + note, auto-updating progress bar toward your goal, full history table, goal-aware badge count |
| **AI** | Offline rule-based assistant, quick-question chips, answers grounded in your real profile and program |
| **حسابي (Profile)** | Name / gender / goal / age / height / weight setup, computed plan summary (split, calories, protein, water, BMI), badge grid |

## 📴 Offline behavior

Workouts, exercise images (illustrations + any bundled real photos), profile
data, weight history, badges, nutrition targets, and the AI assistant all
work **fully offline** — nothing in this app requires an internet
connection at runtime. The optional `photos/<gender>/` real-photo layer is
also bundled into the APK at build time, so it works offline too; it is not
fetched live from the device.

## 🖼️ About the exercise images

- **Illustrations** (`images/male/*.svg`, `images/female/*.svg`) are
  original, hand-built vector diagrams — one style per gender — created
  specifically for this app, so there are no licensing concerns.
- **Real photos** (`photos/male/*.jpg`, `photos/female/*.jpg`) are
  downloaded at build time from Wikimedia Commons by
  `.github/workflows/build-apk.yml`, verified to actually be images (not a
  broken link), and copied into both gender folders. Full attribution and
  license notes are in `app/src/main/assets/www/PHOTO_CREDITS.md`.
- If you replace or add photos yourself, make sure you actually hold the
  rights to distribute them inside the app (your own photos, or
  royalty-free sources with a license that permits redistribution).

## 🛠️ Build with GitHub Actions

The repository includes `.github/workflows/build-apk.yml`. On every push to
`main` (or manual dispatch), it:

1. Sets up Java 17, the Android SDK, and Gradle.
2. Downloads and verifies real exercise photos from Wikimedia Commons.
3. Copies them into `photos/male/` and `photos/female/`.
4. Builds the debug APK.
5. Uploads it as artifact **`IRON-ASCENT-2.2.0-APK`**
   (`IRON-ASCENT-2.2.0.apk`).

## 📦 Download

Latest APK releases:

https://drive.google.com/drive/folders/1crLIsu3hn63VU13fN3Q-QbLTXSrVxIzq?usp=sharing

## 🔐 Permissions

- `POST_NOTIFICATIONS` — meal reminder notifications.
- `RECEIVE_BOOT_COMPLETED` — re-schedules meal reminders after a device
  restart or app update.

## ⚠️ AI note

IRON AI does **not** call any external AI API and no API key is embedded in
the app. It is a local, rule-based assistant that reads your saved profile
and computed nutrition/training targets to answer common questions
instantly and offline. It is not a substitute for a doctor or a certified
coach, especially for medical concerns or injuries.

## 👨‍💻 Developer

**Abdelrhman Hamed**

Copyright © 2026 IRON ASCENT.
