using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CandyPoint : MonoBehaviour
{
    GameObject gm;
    // Start is called before the first frame update
    private void Awake()
    {
        gm = GameObject.Find("GM");
    }

    void Start()
    {
        //gm = GameObject.FindGameObjectWithTag("PointsMenager");   
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.name.Equals("Player"))
        {
            gm.GetComponent<PointsGM>().addPoint();
            Destroy(gameObject);
        }
    }
}
