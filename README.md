# todoapp

## Step-by-Step Guide to Clone and Push a TodoApp Project

### Step 1: Clone the Existing Repository

1. Open your terminal (or Git Bash on Windows).
2. Clone the provided repository containing the `src` folder using the following command:

   ```bash
   git clone https://github.com/swayaan-training/todoapp.git
   ```

   This will create a local copy of the repository in your current directory.

3. Navigate to the cloned repository:

   ```bash
   cd todoapp
   ```

### Step 2: Create Your Own GitHub Repository

1. Log in to your [GitHub account](https://github.com/).
2. Click on the **New** button or go to **Repositories** → **New** to create a new repository.
3. Give your repository a name (for example: `TodoApp`).
4. Do not check the option to **Initialize this repository with a README** (since you're going to push an existing project).
5. Click **Create repository**.

### Step 3: Push the `src` Folder to Your Own Repository

1. In your terminal, configure Git to use your GitHub username and email:

   ```bash
   git config --global user.name "your-github-username"
   git config --global user.email "your-email@example.com"
   ```

2. Set your own repository as the remote origin:

   ```bash
   git remote add origin https://github.com/your-github-username/TodoApp.git
   ```

3. Add the `src` folder for tracking:

   ```bash
   git add .
   ```

4. Commit the changes:

   ```bash
   git commit -m "Added TodoApp src folder"
   ```

5. Push the changes to your own GitHub repository:

   ```bash
   git push -u origin main
   ```

### Step 4: Verify the Push

1. Go back to your GitHub account and navigate to your newly created repository.
2. You should see the `src` folder uploaded successfully.
