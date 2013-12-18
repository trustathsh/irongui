package de.hshannover.f4.trust.irongui.view.subscription;




import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import de.hshannover.f4.trust.irongui.view.ViewController;

public class QuickSubscribePanel extends JPanel {

	private static final long serialVersionUID = 4532218114117343260L;
	public final QuickMetaSubscribe mMetaPanel;
	public final JButton buttonSubscribe;
	private ViewController mViewController;
	private final JComponent mQuickComponent;
	
	public QuickSubscribePanel(JComponent c, ViewController vc){
		mViewController = vc;
		mQuickComponent = c;
		setLayout(new BorderLayout(0, 0));
		add(c, BorderLayout.NORTH);
		mMetaPanel = new QuickMetaSubscribe();
		add(mMetaPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel, BorderLayout.SOUTH);
		
		buttonSubscribe = new JButton("Subscribe");
		buttonSubscribe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mViewController.subscribe();
			}
		});
		panel.add(buttonSubscribe);
	}
	
	public JComponent getQuickComponent() {
		return mQuickComponent;
	}
}
