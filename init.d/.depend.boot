TARGETS = console-setup ufw mountkernfs.sh resolvconf apparmor x11-common hostname.sh screen-cleanup plymouth-log udev keyboard-setup networking hwclock.sh urandom rpcbind mountdevsubfs.sh checkroot.sh mountall-bootclean.sh mountall.sh bootmisc.sh kmod checkroot-bootclean.sh checkfs.sh procps mountnfs-bootclean.sh mountnfs.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
networking: mountkernfs.sh urandom resolvconf procps
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
rpcbind: networking
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh udev mountnfs-bootclean.sh
kmod: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
checkfs.sh: checkroot.sh
procps: mountkernfs.sh udev
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking rpcbind
