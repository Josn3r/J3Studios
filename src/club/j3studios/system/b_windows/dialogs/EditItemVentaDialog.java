package club.j3studios.system.b_windows.dialogs;

import club.j3studios.system.utils.visuals.Background;
import club.j3studios.system.utils.visuals.ButtonCustom;
import club.j3studios.system.utils.visuals.Glass;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class EditItemVentaDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;

    public EditItemVentaDialog(JFrame fram) {
        super(fram, true);
        this.fram = fram;
        initComponents();
        init();
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        //doc.setParagraphAttributes(0, doc.getLength(), center, false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass();
    }

    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }

    public void showMessage(String title, String message) {
        fram.setGlassPane(glass);
        glass.setVisible(true);
        lbTitle.setText(title);
        txt.setText(message);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }

    public void closeMessage() {
        startAnimator(false);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    private void initComponents() {

        background1 = new Background();
        cmdCancel = new ButtonCustom();
        cmdOK = new ButtonCustom();
        lbIcon = new JLabel();
        lbTitle = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        cmdCancel.setBackground(new Color(245, 71, 71));
        cmdCancel.setText("Cancelar");
        cmdCancel.setColorHover(new Color(255, 74, 74));
        cmdCancel.setColorPressed(new Color(235, 61, 61));
        cmdCancel.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        cmdOK.setText("Aceptar");
        cmdOK.setFont(new Font("sansserif", 0, 14)); // NOI18N
        cmdOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        lbIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lbIcon.setIcon(new ImageIcon(getClass().getResource("/images/deletingIcon.png"))); // NOI18N

        lbTitle.setFont(new Font("sansserif", 1, 18)); // NOI18N
        lbTitle.setForeground(new Color(245, 71, 71));
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setText("Message Title");
        
        txt = new JFormattedTextField();
        txt.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
        txt.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout background1Layout = new GroupLayout(background1);
        background1Layout.setHorizontalGroup(
        	background1Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        		.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        		.addGroup(Alignment.TRAILING, background1Layout.createSequentialGroup()
        			.addComponent(cmdCancel, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(cmdOK, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
        		.addGroup(background1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txt, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        			.addContainerGap())
        );
        background1Layout.setVerticalGroup(
        	background1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(background1Layout.createSequentialGroup()
        			.addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(lbTitle)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txt, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        			.addGap(18)
        			.addGroup(background1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cmdCancel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        				.addComponent(cmdOK, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
        );
        background1.setLayout(background1Layout);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(background1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(background1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Integer getNewCantidad() {
    	return Integer.valueOf(txt.getText());
    }
    
    private void cmdCancelActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }

    private void cmdOKActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        messageType = MessageType.OK;
        closeMessage();
    }

    public static enum MessageType {
        CANCEL, OK
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Background background1;
    private ButtonCustom cmdCancel;
    private ButtonCustom cmdOK;
    private JLabel lbIcon;
    private JLabel lbTitle;
    private JFormattedTextField txt;
}
