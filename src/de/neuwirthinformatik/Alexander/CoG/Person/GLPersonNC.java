package de.neuwirthinformatik.Alexander.CoG.Person;

import GLOOP.GLVektor;

public class GLPersonNC extends PMC
{
	public GLVektor direction;
	public int move=0;
	public int lastmove=0;
	int dir =2;
	
    public Head pm_head;
    public Neck pm_neck;
    public Body pm_body;
    public Gun pm_gun;
    public ViewDirection pm_dir;
    
    public PMC[] arms = new PMC[2];
    public PMC[] lower_arms = new PMC[2];
    public PMC[] legs = new PMC[2];
    public PMC[] lower_legs = new PMC[2];
    //2Do
    public Position[] pm_positions = new Position[2];
    public UpperLeg[] pm_upperlegs = new UpperLeg[2];
    public Knee[] pm_knees = new Knee[2];
    public LowerLeg[] pm_lowerlegs = new LowerLeg[2];
    public Foot[] pm_foots = new Foot[2];
    public Shoulder[] pm_shoulders = new Shoulder[2];
    public UpperArm[] pm_upperarms = new UpperArm[2];
    public Elbow[] pm_elbows = new Elbow[2];
    public LowerArm[] pm_lowerarms = new LowerArm[2];
    public Hand[] pm_hands = new Hand[2];
    
    public GLPersonNC(double x, double y, double z, double body_r, double body_sy,double neck_r, double neck_sy, double head_r, double leg_r, double upperleg_sy, double lowerleg_sy,double foot_r, double foot_sz, double arm_r, double upperarm_sy, double lowerarm_sy, double hand_r, double hand_sz)
    {
        //head_r/2 no /2
    	pm_dir = new ViewDirection();
    	
        pm_head = new Head(this,x,y+body_sy/2+neck_sy+head_r/2,z,head_r);
        pm_neck = new Neck(this,x,y+body_sy/2+neck_sy/2,z,neck_r,neck_sy);
        pm_body = new Body(this,x,y,z,body_r,body_sy);
        
        pm_upperlegs[0] = new UpperLeg(this,x+body_r-leg_r,y-body_sy/2-upperleg_sy/2,z,leg_r, upperleg_sy);
        pm_upperlegs[1] = new UpperLeg(this,x-body_r+leg_r,y-body_sy/2-upperleg_sy/2,z,leg_r, upperleg_sy);
        
        pm_positions[0] = new Position(this,pm_upperlegs[0].gibX(),pm_upperlegs[0].gibY()+upperleg_sy/2,pm_upperlegs[0].gibZ());
        pm_positions[1] = new Position(this,pm_upperlegs[1].gibX(),pm_upperlegs[1].gibY()+upperleg_sy/2,pm_upperlegs[1].gibZ());
        //knee_r/2 no /2
        pm_knees[0] = new Knee(this,x+body_r-leg_r,y-body_sy/2-upperleg_sy-leg_r/2,z,leg_r);
        pm_knees[1] = new Knee(this,x-body_r+leg_r,y-body_sy/2-upperleg_sy-leg_r/2,z,leg_r);
        //watch knee_r/2!!!! leg_r!!!
        pm_lowerlegs[0] = new LowerLeg(this,x+body_r-leg_r,y-body_sy/2-upperleg_sy-leg_r-lowerleg_sy/2,z,leg_r,lowerleg_sy);
        pm_lowerlegs[1] = new LowerLeg(this,x-body_r+leg_r,y-body_sy/2-upperleg_sy-leg_r-lowerleg_sy/2,z,leg_r,lowerleg_sy);
        
        pm_foots[0] = new Foot(this,x+body_r-leg_r,y-body_sy/2-upperleg_sy-leg_r-lowerleg_sy-foot_r,z+foot_sz/2-leg_r,foot_r,foot_sz);
        pm_foots[1] = new Foot(this,x-body_r+leg_r,y-body_sy/2-upperleg_sy-leg_r-lowerleg_sy-foot_r,z+foot_sz/2-leg_r,foot_r,foot_sz);
        
        pm_shoulders[0] = new Shoulder(this,x+body_r+arm_r,y+body_sy/2-arm_r,z,arm_r);
        pm_shoulders[1] = new Shoulder(this,x-body_r-arm_r,y+body_sy/2-arm_r,z,arm_r);
        
        pm_upperarms[0] = new UpperArm(this,x+body_r+arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy/2,z,arm_r,upperarm_sy);
        pm_upperarms[1] = new UpperArm(this,x-body_r-arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy/2,z,arm_r,upperarm_sy);
        
        pm_elbows[0] = new Elbow(this,x+body_r+arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy-arm_r/2,z,arm_r);
        pm_elbows[1] = new Elbow(this,x-body_r-arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy-arm_r/2,z,arm_r);
        
        pm_lowerarms[0] = new LowerArm(this,x+body_r+arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy-arm_r-lowerarm_sy/2,z,arm_r,lowerarm_sy);
        pm_lowerarms[1] = new LowerArm(this,x-body_r-arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy-arm_r-lowerarm_sy/2,z,arm_r,lowerarm_sy);
        
        pm_hands[0] = new Hand(this,x+body_r+arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy-arm_r-lowerarm_sy-hand_r,z+hand_sz/2-arm_r,hand_r,hand_sz);
        pm_hands[1] = new Hand(this,x-body_r-arm_r,y+body_sy/2-arm_r-arm_r/2-upperarm_sy-arm_r-lowerarm_sy-hand_r,z+hand_sz/2-arm_r,hand_r,hand_sz);
        
        pm_gun = new Gun(this,pm_hands[1].gibX(),pm_hands[1].gibY(),pm_hands[1].gibZ());
        //jetpack = new JetPack(this, x,y,z-body_r- jet_l/2,jet_r, jet_l,jet_r_z, jet_l_z);
        
        //| Main
        add(pm_body);
        add(pm_head);
        add(pm_neck);
        add(pm_positions);
        add(pm_upperlegs);
        add(pm_knees);
        add(pm_lowerlegs);
        add(pm_foots);
        add(pm_shoulders);
        add(pm_upperarms);
        add(pm_elbows);
        add(pm_lowerarms);
        add(pm_hands);
        add(pm_gun);
        add(pm_dir);
        
        arms[0] = new PMC();
        arms[0].add(pm_shoulders[0]);
        arms[0].add(pm_upperarms[0]);
        arms[0].add(pm_elbows[0]);
        arms[0].add(pm_lowerarms[0]);
        arms[0].add(pm_hands[0]);

        lower_arms[0] = new PMC();
        lower_arms[0].add(pm_elbows[0]);
        lower_arms[0].add(pm_lowerarms[0]);
        lower_arms[0].add(pm_hands[0]);
        
        arms[1] = new PMC();
        arms[1].add(pm_shoulders[1]);
        arms[1].add(pm_upperarms[1]);
        arms[1].add(pm_elbows[1]);
        arms[1].add(pm_lowerarms[1]);
        arms[1].add(pm_hands[1]);
        arms[1].add(pm_gun);
        
        lower_arms[1] = new PMC();
        lower_arms[1].add(pm_elbows[1]);
        lower_arms[1].add(pm_lowerarms[1]);
        lower_arms[1].add(pm_hands[1]);
        lower_arms[1].add(pm_gun);

        legs[0] = new PMC();
        legs[0].add(pm_positions[0]);
        legs[0].add(pm_upperlegs[0]);
        legs[0].add(pm_knees[0]);
        legs[0].add(pm_lowerlegs[0]);
        legs[0].add(pm_foots[0]);

        lower_legs[0] = new PMC();
        lower_legs[0].add(pm_knees[0]);
        lower_legs[0].add(pm_lowerlegs[0]);
        lower_legs[0].add(pm_foots[0]);

        legs[1] = new PMC();
        legs[1].add(pm_positions[1]);
        legs[1].add(pm_upperlegs[1]);
        legs[1].add(pm_knees[1]);
        legs[1].add(pm_lowerlegs[1]);
        legs[1].add(pm_foots[1]);

        lower_legs[1] = new PMC();
        lower_legs[1].add(pm_knees[1]);
        lower_legs[1].add(pm_lowerlegs[1]);
        lower_legs[1].add(pm_foots[1]);
        
        //add(arms);
        //add(legs);
        arms[1].dreheDich(-90,0,0);
    }
    
    public synchronized void move(double x, double y, double z)
    {
    	GLVektor v = new GLVektor(pm_dir.gibX(),pm_dir.gibY(),pm_dir.gibZ());
    	v.normalisiere();
    	v.multipliziere(z);
    	this.verschiebe(v.x, v.y, v.z);
    	v = new GLVektor(pm_dir.gibX(),pm_dir.gibY(),pm_dir.gibZ());
    	v.normalisiere();
    	v.drehe(0, 90, 0);
    	v.multipliziere(x);
    	this.verschiebe(v.x, v.y, v.z);
    	v = new GLVektor(pm_dir.gibX(),pm_dir.gibY(),pm_dir.gibZ());
    	v.normalisiere();
    	v.drehe(90, 0, 0);
    	v.multipliziere(y);
    	this.verschiebe(v.x, v.y, v.z);
    }
    
    public synchronized void update()
    {

    	if(lastmove != move)
    	{
    		double trotx,troty,trotz;
    		trotx=this.gibRotX();
    		troty=this.gibRotY();
    		trotz=this.gibRotZ();
    		this.setzeDrehung(0,0,0);
    		this.legs[0].setzeDrehung(0, 0, 0);
    		this.legs[1].setzeDrehung(0, 0, 0);
    		this.lower_legs[0].setzeDrehung(0, 0, 0);
    		this.lower_legs[1].setzeDrehung(0, 0, 0);
    		this.dreheDich(trotx,troty,trotz);
    	}
    	
    	if (move==1||move==2)      //forward,backw
    	{
    		if(this.legs[0].gibRotX()<-25 || this.legs[0].gibRotX()>25)dir*=-1;
    		double trotx,troty,trotz;
    		trotx=this.gibRotX();
    		troty=this.gibRotY();
    		trotz=this.gibRotZ();
    		this.setzeDrehung(0,0,0);
    		this.legs[0].dreheDich(-0.1*dir, 0, 0);
    		this.lower_legs[0].dreheDich(0.05*dir,0,0);
    		this.legs[1].dreheDich(0.1*dir, 0, 0);
    		this.lower_legs[1].dreheDich(-0.05*dir,0,0);
    		this.dreheDich(trotx,troty,trotz);
    	}
    	
    	if(move==3||move==4)
    	{
    		if(this.legs[0].gibRotZ()<-10 || this.legs[0].gibRotZ()>10)dir*=-1;
    		double trotx,troty,trotz;
    		trotx=this.gibRotX();
    		troty=this.gibRotY();
    		trotz=this.gibRotZ();
    		this.setzeDrehung(0,0,0);
    		this.legs[0].dreheDich(0, 0, 0.1*dir);
    		this.lower_legs[0].dreheDich(0,0,-0.05*dir);
    		this.legs[1].dreheDich(0, 0, -0.1*dir);
			this.lower_legs[1].dreheDich(0,0,0.05*dir);
    		this.dreheDich(trotx,troty,trotz);
    	}
    	lastmove = move;
    }
}

