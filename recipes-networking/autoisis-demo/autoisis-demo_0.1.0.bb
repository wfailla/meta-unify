inherit systemd

SRC_URI = "file://demo-config.service \
           file://setup_net.sh"

SYSTEMD_SERVICE_${PN}= "demo-config.service"

LICENSE="CLOSED"
PR="r3"

do_install_append() {
    install -d -m 0755 "${D}/usr/sbin"
    install -m 0755 "${WORKDIR}/setup_net.sh" "${D}/usr/sbin/setup_net.sh"

    install -d -m 0755 "${D}/${systemd_unitdir}/system"
    install -m 0644 "${WORKDIR}/demo-config.service" "${D}/${systemd_unitdir}/system"
}
