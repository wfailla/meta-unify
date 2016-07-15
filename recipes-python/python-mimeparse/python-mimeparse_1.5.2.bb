DESCRIPTION = "basic functions for parsing mime-type names and matching "
HOMEPAGE = "https://pypi.python.org/pypi/python-mimeparse/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=838c366f69b72c5df05c96dff79b35f2"

PR = "r1"

SRCNAME = "python-mimeparse"

SRC_URI = "https://pypi.python.org/packages/9c/ea/148511af6f5a6889f4d3b90b00e817b4380658fd0def82a85ee83eddfa45/${PN}-${PV}.tar.gz"

SRC_URI[md5sum] = "e0e491df099aedc20e05e8a74f1250d8"
SRC_URI[sha256sum] = "bef134a59598cc6aa598f84553162aa7a0c01f3f431588225bb9a208964b1827"

DEPENDS="python-pip"

inherit setuptools
