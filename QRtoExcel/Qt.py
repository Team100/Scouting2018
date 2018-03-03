import sys
from PyQt5 import QtCore, QtGui, QtWidgets
from ui.teamselect import Ui_MainWindow
from ui.details import Ui_Dialog

class AppWindow(Ui_MainWindow):
    def __init__(self, dialog):
	    Ui_MainWindow.__init__(self)
	    self.setupUi(dialog)
	    self.initUI()
    def initUI(self):
            self.pushButton.clicked.connect(self.onClick)
    def onClick(self):
            self.textEdit.setText("Hello")
            d = QtWidgets.QDialog()
            dialog = DetailsDialog(d)
            d.show()

class DetailsDialog(Ui_Dialog):
    def __init__(self, dialog):
	    Ui_MainWindow.__init__(self)
	    self.setupUi(dialog)
	    self.initUI()
        
if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    w = QtWidgets.QMainWindow()
    d = QtWidgets.QDialog()
    window = AppWindow(w)
    w.show()
    sys.exit(app.exec_())
