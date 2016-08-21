SUMMARY = "Networking and Cryptography library"
DESCRIPTION = "PyNaCl is a Python binding to the Networking and Cryptography library, a crypto library with the stated goal of improving usability, security and speed."
HOMEPAGE = "https://github.com/pyca/pynacl"
SECTION = "other"

PR = "r1"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

inherit setuptools3

RDEPENDS_${PN} += "python3"

SRC_URI = "https://github.com/pyca/pynacl/archive/1.0.1.tar.gz"
SRC_URI[md5sum] = "7991f7453a832316d78f8efd9019e9cb"
SRC_URI[sha256sum] = "033fb4db0598773b34863b0bebf0e64985712aec449513e78bfe2afc410cd759"
