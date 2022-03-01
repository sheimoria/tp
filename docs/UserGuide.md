---
layout: page
title: User Guide
---

onlyFAs is for high achieving financial advisors looking to maintain and develop good relationships with current and potential clients. It allows users to maintain an all-encompassing client manager app that can easily arrange for meetups with clients. Currently, financial advisors might have to rely on apps like Google Calendar, and do not have a dedicated platform catered to helping them manage their clientele.


- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `onlyfas.jar` **(Coming Soon!)**  <!--- from [here](https://github.com/se-edu/addressbook-level3/releases). -->

1. Copy the file to the folder you want to use as the **home folder** for _onlyFAs_.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - **`list`** : Lists all contacts.

   - **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to your contact list.

   - **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   - **`clear`** : Deletes all contacts.

   - **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Add meeting: `meeting`

Add a meeting to your schedule.

Format: `meeting e/EMAIL d/DATE t/TIME`

Examples:

- `meeting e/vijay@email.com d/2/2/2022 t/1200`
- `meeting e/vijay@email.com d/14/12/2022 t/1400`

### Delete client: `delete client`

Delete a client from your address book.

Format: `delete client EMAIL`

Examples:

- `delete client vijay@email.com`

### Delete client: `view client`

View a client in your address book.

Format: `view client EMAIL`

Examples:

- `view client vijay@email.com`

### Delete client: `update client`

Update a client from in your address book.

Format: `update client e/EMAIL [nn/NEW_NAME np/NEW_PHONE_NUMBER ne/NEW_EMAIL]`

Examples:

- `update client e/vijay@email.com ne/new@email.com`

### Clearing all entries : `clear`

Clear all entries in onlyFAs.

Format: `clear`

### Exiting the program : `exit`

Exit the program.

Format: `exit`

### Saving the data

onlyFAs' data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

onlyFAs' data is saved in a JSON file `[JAR file location]/data/data.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, onlyFAs will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous onlyFAs home folder.

---

## Mockups

<img src="/images/mockups/zoom-in-client.png" width="200px">

---

## Command summary

| Action            | Format, Examples                                                                                                                   |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------|
| **z 1**           | `` <br> e.g., ``                                                                                                                   |
| **z 2**           | `` <br> e.g., ``                                                                                                                   |
| **z 3**           | `` <br> e.g., ``                                                                                                                   |
| **z 4**           | `` <br> e.g., ``                                                                                                                   |
| **meeting**       | `meeting e/EMAIL d/DATE t/TIME` <br> e.g., `meeting e/vijay@email.com d/14/12/2022 t/1400`                                         |
| **delete client** | `delete client EMAIL` <br> e.g., `delete 1`                                                                                        |
| **view client**   | `view client EMAIL`<br> e.g., `view 1`                                                                                             |
| **update client** | `update client e/EMAIL [nn/NEW_NAME np/NEW_PHONE_NUMBER ne/NEW_EMAIL]`<br> e.g.,`update client e/vijay@email.com ne/new@email.com` |
| **j 1**           | `` <br> e.g., ``                                                                                                                   |
| **j 2**           | `` <br> e.g., ``                                                                                                                   |
| **j 3**           | `` <br> e.g., ``                                                                                                                   |
| **j 4**           | `` <br> e.g., ``                                                                                                                   |
| **Help**          | `help`                                                                                                                             |
