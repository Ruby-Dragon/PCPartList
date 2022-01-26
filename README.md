# PCPartList

###### GNU Public Licence v3, 2022, Ruby-Dragon

This project was made for fun and practice of Java.

You make a list of parts in a PC build.

A build has price, power usage, and parts.
You add parts with their name, price, powerdraw(0 for some), and optionally, a link to purchase website.

This is written in Java because I like to suffer apparently.

## Usage:

	To make a new list, use the "new" command, followed by the name of the list.
	Names MUST not have spaces or violate Windows naming rules. Anything after a space will be unused.

	To add a part, use the "add" command followed by part name, price, power consumption, and optionally, a retail link.

	To remove a part, use the "rm" command, followed by part name.

	To edit a part, use the "edit" command, followed by old name, new name, new price, new power consumption, and an optional new retail link.
	All edited parts will no longer be marked purchased.

	To purchase a part, use the "purchase" command, followed by part name. The part will now be marked purchase.
	THIS WILL NOT BUY THE PART!!!

	To delete the list, use the "del" command, followed by list name. THE LIST WILL BE GONE PERMANENTLY, AND IT WILL NOT ASK FOR CONFIRMATION!

	To open an existing list, use the "open" command, followed by list name. Once opened, the add, rm, edit, and purchase commands will be availble.

#### JAVA 11, AT LEAST, REQUIRED
#### Complie.sh is only verified to work on Linux - Windows = maybe, OSX = maybe

## Licence:

This software uses the GPL licence. Read the terms before using the source code.

###### ALL DERIVATIVE WORKS MUST BE GPL v3 LICENCED AS WELL, AND MUST KEEP ALL COPYRIGHT NOTICES IN CODE. See the LICENCE for more information.