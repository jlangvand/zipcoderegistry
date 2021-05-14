# Zip Code Registry

A very simple application for displaying a searchable registry of zip/postal codes.

The current version supports Norwegian zip codes from Bring. The registry file can be downloaded automatically from
Bring, or a local file can be loaded upon starting the application.

## Usage

Upon starting the application, the user will be prompted if they wish to automatically download the latest registry file
from Bring, or if they want to load a registry file from their computer.

The interface consists of a table view, and a search box. When a query is typed in the search box, the contents of the
table will be filtered to show only matching rows. A query can consist of several clauses separated by any non-word
character:
`tron 74` would match all zip codes starting with 74 in Trondheim. 