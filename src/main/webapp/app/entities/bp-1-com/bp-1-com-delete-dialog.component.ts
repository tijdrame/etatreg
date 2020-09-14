import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp1Com } from 'app/shared/model/bp-1-com.model';
import { Bp1ComService } from './bp-1-com.service';

@Component({
  templateUrl: './bp-1-com-delete-dialog.component.html',
})
export class Bp1ComDeleteDialogComponent {
  bp1Com?: IBp1Com;

  constructor(protected bp1ComService: Bp1ComService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp1ComService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp1ComListModification');
      this.activeModal.close();
    });
  }
}
