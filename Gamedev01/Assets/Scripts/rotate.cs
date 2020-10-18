using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class rotate : MonoBehaviour
{
    public GameObject poly;
    private Rigidbody2D skeleton;
    private int tmp = 0;
    // Start is called before the first frame update
    void Start()
    {
        skeleton = gameObject.GetComponent<Rigidbody2D>();

    }

    // Update is called once per frame
    void Update()
    {

        if (tmp < 4000)
        {
            transform.Rotate(new Vector3(0, 0, 1));
            // transform.localScale += new Vector3(0.1f, 0.1f, 0.1f);
            // transform.Translate(new Vector3(0.1f, 0, 0));
            tmp++;
        }
    }
}
