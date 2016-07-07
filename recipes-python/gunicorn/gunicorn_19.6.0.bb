SUMMARY="Gunicorn a Python WSGI HTTP Server for UNIX"
DESCRIPTION="Gunicorn 'Green Unicorn' is a Python WSGI HTTP Server for UNIX. It's a pre-fork worker model ported from Ruby's Unicorn project."
LICENSE="CLOSED"

PR="r0"

SRC_URI = "https://github.com/benoitc/gunicorn/archive/${PV}.tar.gz"

SRC_URI[md5sum] = "611d8b12c5f919a5c916dc6e563b9523"
SRC_URI[sha256sum] = "1e0de4957bea60bfcff5215664bdfc3cf02c78e2aae9586766a9b4b437aebbb0"

DEPENDS="python-pip"
RDEPENDS_${PN} = "python-pip"

FILES_${PN} = "/usr/local/lib/python2.7/dist-packages/gunicorn \
  /usr/local/lib/python2.7/dist-packages/gunicorn-19.6.0.egg-info \
  /usr/local/bin"

do_install() {
  pip install -U ${S} --root ${D}
}
