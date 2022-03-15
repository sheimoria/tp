iss---
layout: page
title: User Guide
---

onlyFAs is for high achieving financial advisors looking to maintain and develop good relationships with current and potential clients. It allows users to maintain an all-encompassing client manager app that can easily arrange for meetups with clients. Currently, financial advisors might have to rely on apps like Google Calendar, and do not have a dedicated platform catered to helping them manage their clientele.

- [Quick start](#quick-start)
- [Features](#features)
    * [Viewing help : `help`](#viewing-help----help-)
    * [Add client: `client`](#add-client---client-)
    * [Add notes for client: `note`](#add-notes-for-client---note-)
    * [Add policy: `policy`](#add-policy---policy-)
    * [Add preferences to client: `preference`](#add-preferences-to-client---preference-)
    * [Add meeting: `meeting`](#add-meeting---meeting-)
    * [Delete client: `delete client`](#delete-client---delete-client-)
    * [View client: `view client`](#view-client---view-client-)
    * [Update client: `update client`](#update-client---update-client-)
    * [Listing All Meetings: `list meetings`](#listing-all-meetings---list-meetings-)
    * [View Meeting with Client : `view meeting`](#view-meeting-with-client----view-meeting-)
    * [Update Meeting Details: `update meeting`](#update-meeting-details---update-meeting-)
    * [Clearing all entries : `clear`](#clearing-all-entries----clear-)
    * [Exiting the program : `exit`](#exiting-the-program----exit-)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
    * [Archiving data files `[coming in v2.0]`](#archiving-data-files---coming-in-v20--)
- [FAQ](#faq)
- [Mockups](#mockups)
- [Command summary](#command-summary)

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

### Add client: `client`

Adds a client to the list of managed clients

Format: `client e/EMAIL no/NUMBER n/NAME`

Examples:

- `client e/vijay@gmail.com no/99134234 n/N Vijay Narayanan`
- `client e/zechary@yeemail.com no/92144213 n/Zechary Au Jun Wen`

### Add notes for client: `note`

Adds a plaintext note to a specific client

Format: `note e/EMAIL nt/NOTE`

- `note e/vijay@gmail.com nt/Commando NSF with high risk of injury`
- `note e/zechary@yeemail.com nt/Look up on policies for emergency care`

### Add policy: `policy`

Adds an insurance policy to a specific client

Format: `policy e/EMAIL t/TITLE v/VALUE d/DESCRIPTION`

Examples:

- `policy e/vijay@gmail.com t/Medicare Plus v/1 million dollars d/Payment until 25`
- `policy e/zechary@yeemail.com t/Car Insurance v/100 thousand d/250 each month`

### Add preferences to client: `preference`

Adds a preference to a specific client

Format: `preference e/EMAIL tag/TAG d/DESCRIPTION`

Examples:

- `preference e/vijay@gmail.com tag/Drink d/Coke`
- `preference e/zechary@yeemail.com tag/Cigarettes d/Malboro`

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

### View client: `view client`

View a client in your address book.

Format: `view client EMAIL`

Examples:

- `view client vijay@email.com`

### Update client: `update client`

Update a client from in your address book.

Format: `update client e/EMAIL [nn/NEW_NAME np/NEW_PHONE_NUMBER ne/NEW_EMAIL]`

Examples:

- `update client e/vijay@email.com ne/new@email.com`

### Listing All Meetings: `list meetings`

Shows all upcoming meetings.

Format: `list meetings`

### View Meeting with Client : `view meeting`

View details of a particular meeting.

Format: `view meeting meetingId`

Examples:

- `view meeting 9`

### Update Meeting Details: `update meeting`

Update details of a particular meeting.

Format: `update meeting meetingId`

Examples:

- `update meeting 9`

###Save to hard drive

OnlyFAs’s data is saved in the hard drive automatically after any command that changes the data. There is no need to save manually.

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

| Action             | Format, Examples                                                                                                                         |
|--------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| **client**         | `client e/EMAIL no/NUMBER n/NAME` <br> e.g., `e/vijay@gmail.com no/99134234 n/N Vijay Narayanan`                                         |
| **note**           | `note e/EMAIL nt/NOTE` <br> e.g., `note e/vijay@gmail.com nt/Commando NSF with high risk of injury`                                      |
| **policy**         | `policy e/EMAIL t/TITLE v/VALUE d/DESCRIPTION` <br> e.g., `policy e/zechary@yeemail.com t/Car Insurance v/100 thousand d/250 each month` |
| **preference**     | `preference e/EMAIL tag/TAG d/DESCRIPTION` <br> e.g., `preference e/zechary@yeemail.com tag/Cigarettes d/Malboro`                        |
| **meeting**        | `meeting e/EMAIL d/DATE t/TIME` <br> e.g., `meeting e/vijay@email.com d/14/12/2022 t/1400`                                               |
| **delete client**  | `delete client EMAIL` <br> e.g., `delete vijay@email.com`                                                                                |
| **view client**    | `view client EMAIL`<br> e.g., `view vijay@email.com`                                                                                     |
| **update client**  | `update client e/EMAIL [nn/NEW_NAME np/NEW_PHONE_NUMBER ne/NEW_EMAIL]`<br> e.g.,`update client e/vijay@email.com ne/new@email.com`       |
| **list meetings**  | `shows all upcoming meetings` <br> e.g., `list meetings`                                                                                 |
| **view meeting**   | `view meeting INDEX` <br> e.g., `view meeting 9`                                                                                         |
| **update meeting** | `update meeting INDEX` <br> e.g., `update meeting 9`                                                                                     |
| **Help**           | `help`                                                                                                                                   |
