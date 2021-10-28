import hashlib
from os import system


def setup():
    # Watermark
    system("cls")
    print("\033[4;34;40mHash Checker by Gullen \033[0;37;40m")
    print()

    # File Selector
    print("To start, please enter the filename (if in the same directory) or the location of the file to check")
    file = input("> ")
    try:
        open(file, 'rb')
    except Exception:
        print("\033[1;31;40m")
        print("Incorrect file / location. Try again making sure to include the file extension (e.g. '.exe') \033[1;37;40m")
        print()
        input("Press enter to try again")
        setup()

    print()
    print()

    # Algorithm Selector
    print("Please chose a supported hashing algorithm (e.g. 'MD5' 'SHA256')")
    algorithm = input("> ")
    if algorithm.upper() not in ["MD5", "SHA256"]:
        print("\033[1;31;40m")
        print("Unsupported Algorithm selected, Check readme for supported algorithms. \033[1;37;40m")
        print()
        input("Press enter to try again")
        setup()

    print()
    print()

    # Known HASH
    print("If you know the HASH please enter it now, else leave blank")
    knownHash = input("> ")

    hashCheck(file, algorithm, knownHash)


def hashCheck(file, algorithm, knownHash):
    fileData = open(file, 'rb').read()

    # Hash file
    if algorithm.upper() == "MD5":
        hashData = hashlib.md5(fileData).hexdigest()
    elif algorithm.upper() == "SHA256":
        hashData = hashlib.sha256(fileData).hexdigest()

    # Check if hash matches
    if knownHash.upper() == hashData.upper():
        status = "\033[0;37;42m P A S S I N G \033[0;37;40m"
    else:
        status = "\033[0;37;41m F A I L I N G \033[0;37;40m"

    # Display Information
    system("cls")
    print("                            ===================")
    print("                            R  E  S  U  L  T  S")
    print("                            ===================")
    print()
    print("\033[1;33;40m")
    print("File: "          + file)
    print("Algorithm:     " + algorithm.upper())
    print("Hash to check: " + knownHash.upper())
    print()
    print("\033[0;37;40m")
    print("HASH calculated: " + hashData.upper())
    print("HASH given     : " + knownHash.upper())
    print()
    print("STATUS: " + status)
    print()
    print()
    print("\033[1;30;40mPress enter to start again")
    input()
    setup()


setup()