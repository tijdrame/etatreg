import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp2Com } from 'app/shared/model/bp-2-com.model';
import { Bp2ComService } from './bp-2-com.service';

@Component({
  templateUrl: './bp-2-com-delete-dialog.component.html',
})
export class Bp2ComDeleteDialogComponent {
  bp2Com?: IBp2Com;

  constructor(protected bp2ComService: Bp2ComService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp2ComService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp2ComListModification');
      this.activeModal.close();
    });
  }
}
