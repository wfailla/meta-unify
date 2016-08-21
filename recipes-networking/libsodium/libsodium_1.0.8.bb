SUMMARY = "libsodium"
DESCRIPTION = "Sodium is a new, easy-to-use software library for encryption, decryption, signatures, password hashing and more."
SECTION = "other"

PR = "r1"

LICENSE = "other"
LIC_FILES_CHKSUM = "file://LICENSE;md5=092a09b78c3be486fac807435bf17b7a"

inherit autotools

S ="${WORKDIR}/git"

SRC_URI = "https://download.libsodium.org/libsodium/releases/libsodium-1.0.8.tar.gz"
SRC_URI[md5sum] = "0a66b86fd3aab3fe4c858edcd2772760"
SRC_URI[sha256sum] = "c0f191d2527852641e0a996b7b106d2e04cbc76ea50731b2d0babd3409301926"
